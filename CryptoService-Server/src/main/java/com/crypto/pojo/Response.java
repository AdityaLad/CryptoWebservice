package com.crypto.pojo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Response {

    private final String uuid;
    private final String content;

    private ErrorResponse errorResponse;

    public Response(String uuid, String content) {
        this.uuid = uuid;
        this.content = content;
    }

    public Response(String uuid, String content, ErrorResponse error) {
        this.uuid = uuid;
        this.content = content;
        errorResponse = error;
    }

    public String getId() {
        return uuid;
    }

    public String getContent() {
        return content;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }


}
