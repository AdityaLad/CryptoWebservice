package com.crypto.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.crypto.dto.ResponseDTO;

public class SymmetricHelperTest {

    private AESEncryptionHelper symmetricHelper;

    @Before
    public void setUp() throws Exception {
        symmetricHelper = new AESEncryptionHelper();
        symmetricHelper.init();
    }

    @Test
    public void testEncryptionDecryption() throws Exception {
        ResponseDTO result = (ResponseDTO) symmetricHelper.encrypt("My test data");
        ResponseDTO plainText = (ResponseDTO) symmetricHelper.decrypt(result.getResponse().getContent());
        assertEquals("My test data", plainText.getResponse().getContent());
    }
}
