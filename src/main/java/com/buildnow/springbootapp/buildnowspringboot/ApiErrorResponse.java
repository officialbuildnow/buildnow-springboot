package com.buildnow.springbootapp.buildnowspringboot;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class ApiErrorResponse {
    private HttpStatus status;
    private String message;
}
