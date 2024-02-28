package com.buildnow.springbootapp.buildnowspringboot.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}
