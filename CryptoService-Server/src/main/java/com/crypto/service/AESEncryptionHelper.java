package com.crypto.service;

import static org.apache.commons.codec.binary.Hex.decodeHex;
import static org.apache.commons.codec.binary.Hex.encodeHex;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.codec.DecoderException;
import org.springframework.stereotype.Service;

import com.crypto.constants.Constants;
import com.crypto.dto.IDto;
import com.crypto.dto.ResponseDTO;
import com.crypto.pojo.ErrorResponse;
import com.crypto.pojo.Response;

import lombok.extern.slf4j.Slf4j;

/**
 * Generates and keeps the AES symmetric key. encrypt and decrypt methods use the key for encryption and decryption of data.
 * Uses AES key with 256 key size. 
 * 
 * @author Aditya Lad
 *
 */
@Service
@Slf4j
public class AESEncryptionHelper implements ISymmetricEncryptionHelper {
   
    private Map<String, SymmetricKey> symmetricKeys = new HashMap<String, SymmetricKey>();
    
    /**
     * Generates the AES key upon startup.
     */
    @PostConstruct
    public void init() {
        KeyGenerator keyGenerator;
        try {
            keyGenerator = KeyGenerator.getInstance(Constants.AES, Constants.SUN_JCE);
            keyGenerator.init(Constants.AES_KEY_SIZE);
            SecretKey secretKey = keyGenerator.generateKey();
            final String uuid = UUID.randomUUID().toString();
            String keyClass = "AES-256-CBC-PKCS5";
            SymmetricKey key = new SymmetricKey(uuid, keyClass, secretKey);
            symmetricKeys.put(Constants.DEFAULT_KEY, key);
        } catch (NoSuchAlgorithmException e) {
            log.error("NoSuchAlgorithmException", e);
        } catch (NoSuchProviderException e) {
            log.error("NoSuchProviderException", e);
        }
    }

    /**
     * Uses AES and IV for the CBC mode.
     * 
     * @param plainText - Plain text to be encrypted
     * @param secretKey - Symmetric AES key used for encryption
     * @return - encrypted string and initialization vector separated by a delimiter
     * @throws Exception - any exception caused during exception
     */
    private String encrypt(String plainText, SecretKey secretKey) throws Exception {
        Cipher cipher;
        String cipherText = null;
        try {
            byte[] iv = new byte[Constants.AES_256_IV_BLOCKSIZE];
            SecureRandom prng = new SecureRandom();
            prng.nextBytes(iv);
            cipher = Cipher.getInstance(Constants.AES_CBC_PKCS5PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv));
            byte[] bytes = cipher.doFinal(plainText.getBytes());
            cipherText = String.valueOf(encodeHex(bytes));
            String ivText = String.valueOf(encodeHex(iv));
            cipherText = cipherText + Constants.DELIMITER + ivText;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            log.error("NoSuchAlgorithmException | NoSuchPaddingException", e);
            throw e;
        } catch (InvalidKeyException e) {
            log.error("InvalidKeyException", e);
            throw e;
        } catch (IllegalBlockSizeException e) {
            log.error("IllegalBlockSizeException", e);
            throw e;
        } catch (BadPaddingException e) {
            log.error("BadPaddingException", e);
            throw e;
        }
        return cipherText;
    }

    /**
     * @param cryptText - Encrypted text and IV separated by a delimiter
     * @param secretKey - Symmetric AES key used for encryption
     * @return - decrypted plaintext
     * @throws Exception - any exception occurred
     */
    private String decrypt(String cryptText, SecretKey secretKey) throws Exception {
        byte[] cryptBytes;
        byte[] ivBytes;
        byte[] clearBytes = null;
        String[] array = cryptText.split(Constants.DELIMITER_REGEX);
        String encryptedText = array[0];
        String iv = array[1];
        try {
            cryptBytes = decodeHex(encryptedText.toCharArray());
            ivBytes = decodeHex(iv.toCharArray());
            Cipher cipher = Cipher.getInstance(Constants.AES_CBC_PKCS5PADDING);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(ivBytes));
            clearBytes = cipher.doFinal(cryptBytes);
        } catch (DecoderException e) {
            log.error("DecoderException", e);
            throw e;
        } catch (InvalidKeyException e) {
            log.error("InvalidKeyException", e);
            throw e;
        } catch (NoSuchAlgorithmException e) {
            log.error("NoSuchAlgorithmException", e);
            throw e;
        } catch (NoSuchPaddingException e) {
            log.error("NoSuchPaddingException", e);
            throw e;
        } catch (IllegalBlockSizeException e) {
            log.error("IllegalBlockSizeException", e);
            throw e;
        } catch (BadPaddingException e) {
            log.error("BadPaddingException", e);
            throw e;
        }
        log.info("Message decrypted ");
        return new String(clearBytes);
    }

    public IDto encrypt(String plainText){
        SymmetricKey key = symmetricKeys.get(Constants.DEFAULT_KEY);
        String result;
        ResponseDTO dto;
        try {
            result = encrypt(plainText, key.getSecretKey());
        } catch (Exception e) {
            ErrorResponse errorDTO = new ErrorResponse("100", e.getMessage());
            dto = new ResponseDTO(null, errorDTO, true);
            return dto;
        }
        Response response = new Response(key.getId(), result);
        dto = new ResponseDTO(response, null, false);
        return dto;
    }

    public IDto decrypt(String plainText) {
        SymmetricKey key = symmetricKeys.get(Constants.DEFAULT_KEY);
        String result;
        ResponseDTO dto;
        try {
            result = decrypt(plainText, key.getSecretKey());
        }
        catch (Exception e) {
            ErrorResponse errorDTO = new ErrorResponse("101", e.getMessage());
            dto = new ResponseDTO(null, errorDTO, true);
            return dto;
        }
        Response response = new Response(key.getId(), result);
        dto = new ResponseDTO(response, null, false);
        return dto;
    }


}
