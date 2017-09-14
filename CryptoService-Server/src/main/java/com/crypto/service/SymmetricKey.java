package com.crypto.service;

import javax.crypto.SecretKey;

public class SymmetricKey {

    private final String uid;

    private final String keyClass;

    private final SecretKey secretKey;

    public SymmetricKey(String id, String keyClass, SecretKey secretKey) {
        uid = id;
        this.keyClass = keyClass;
        this.secretKey = secretKey;
    }

    public String getId() {
        return uid;
    }

    public String getKeyClass() {
        return keyClass;
    }

    public SecretKey getSecretKey() {
        return secretKey;
    }

}
