package com.crypto.pojo;

public class ErrorResponse {

    private final String errorCode;

    private final String errorMessage;

    public ErrorResponse(String code, String message) {
        errorCode = code;
        errorMessage = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
