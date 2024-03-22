package com.buildnow.springbootapp.buildnowspringboot;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter

public class ApiErrorResponse {
    private int status;
    private String message;

    public ApiErrorResponse(HttpStatus status, String message){
        this.status = status.value();
        this.message = message;
    }
}
