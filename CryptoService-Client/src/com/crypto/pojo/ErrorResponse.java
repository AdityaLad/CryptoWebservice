package com.crypto.pojo;

public class ErrorResponse {

    private final String errorCode;

    private final String errorMessage;

    public ErrorResponse(String code, String message) {
        errorCode = code;
        errorMessage = message;
    }

}
