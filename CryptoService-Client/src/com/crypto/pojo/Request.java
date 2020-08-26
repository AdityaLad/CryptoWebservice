package com.crypto.pojo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Request {

    @JsonProperty("id")
    private String id;

    @JsonProperty("content")
    private String content;

    @JsonCreator
    public Request(@JsonProperty("id") String id, @JsonProperty("content") String content) {
        this.id = id;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
