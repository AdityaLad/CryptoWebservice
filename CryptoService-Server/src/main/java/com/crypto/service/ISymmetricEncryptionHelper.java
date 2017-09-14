package com.crypto.service;

import com.crypto.dto.IDto;

public interface ISymmetricEncryptionHelper {
    public IDto encrypt(String plainText);
    public IDto decrypt(String cipherText);
}
