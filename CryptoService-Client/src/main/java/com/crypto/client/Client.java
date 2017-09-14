package com.crypto.client;

import org.springframework.web.client.RestTemplate;

import com.crypto.pojo.Request;
import com.crypto.pojo.Response;

public class Client {
    
    private static final String KEYSTORE_PASSWORD = "password";
    private static final String URI_ENCRYPT = "https://localhost:8443/encrypt";
    private static final String URI_DECRYPT = "https://localhost:8443/decrypt";
    
    static {
        System.setProperty("javax.net.ssl.trustStore", Client.class.getClassLoader().getResource("clienttruststore.p12").getFile());
        System.setProperty("javax.net.ssl.trustStorePassword", KEYSTORE_PASSWORD);
        System.setProperty("javax.net.ssl.keyStore", Client.class.getClassLoader().getResource("clientkeystore.p12").getFile());
        System.setProperty("javax.net.ssl.keyStorePassword", KEYSTORE_PASSWORD);
    }
    
    public static void main(String[] args) {
        Request encRequest = new Request("1", "Hello world");
        Response encryptedResult = encrypt(encRequest);
        System.out.println("Id:" + encryptedResult.getId());
        System.out.println("Encrypted Content:" + encryptedResult.getContent());
        
        Request decryptRequest = new Request(encryptedResult.getId(), encryptedResult.getContent());
        Response decryptedResult = decrypt(decryptRequest);
        System.out.println("Id:" + decryptedResult.getId());
        System.out.println("Decrypted Content:" + decryptedResult.getContent());
    }
    
    private static Response encrypt(Request request)
    {
        RestTemplate restTemplate = new RestTemplate();
        Response encryptedResponse =  restTemplate.postForObject( URI_ENCRYPT, request, Response.class);
        return encryptedResponse;
    }
    
    private static Response decrypt(Request request){
        RestTemplate restTemplate = new RestTemplate();
        Response decryptedResponse =  restTemplate.postForObject( URI_DECRYPT, request, Response.class);
        return decryptedResponse;
    }

}
