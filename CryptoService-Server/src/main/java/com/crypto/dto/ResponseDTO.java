package com.crypto.dto;

import com.crypto.pojo.ErrorResponse;
import com.crypto.pojo.Response;

public class ResponseDTO implements IDto {

    private final Response response;

    private boolean hasError = false;

    private ErrorResponse errorResponse;

    public ResponseDTO(Response response, ErrorResponse error, boolean isError) {
        this.response = response;
        setErrorResponse(error);
        hasError = isError;
    }

    public boolean hasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public Response getResponse() {
        return response;
    }

}
