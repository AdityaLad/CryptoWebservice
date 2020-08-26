package com.crypto.pojo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Response {
    
    @JsonProperty("id")
    private final String uuid;
    
    @JsonProperty("content")
    private final String content;

    @JsonProperty("errorResponse")
    private ErrorResponse errorResponse;
    
    @JsonCreator
    public Response(@JsonProperty("id") String id, @JsonProperty("content") String content) {
        this.uuid = id;
        this.content = content;
    }
   
   /* public Response(@JsonProperty("id") String uuid, @JsonProperty("content") String content, ErrorResponse error) {
        this.uuid = uuid;
        this.content = content;
        errorResponse = error;
    }*/

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
