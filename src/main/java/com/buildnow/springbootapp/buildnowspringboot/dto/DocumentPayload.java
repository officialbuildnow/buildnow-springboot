package com.buildnow.springbootapp.buildnowspringboot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DocumentPayload {
    private String url;

    public DocumentPayload(
            @JsonProperty("url") String url
    ){
        this.url = url;
    }
}
