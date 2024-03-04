package com.buildnow.springbootapp.buildnowspringboot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminSignUpDTO {
    private String username;
    private String password;

    public AdminSignUpDTO (
            @JsonProperty("username") String username,
            @JsonProperty("password") String password
    ) {
        this.username = username;
        this.password = password;
    }
}
