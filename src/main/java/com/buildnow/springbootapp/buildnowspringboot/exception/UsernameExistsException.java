package com.buildnow.springbootapp.buildnowspringboot.exception;

public class UsernameExistsException extends RuntimeException{
    public UsernameExistsException(String message){
        super(message);
    }
}
