package com.buildnow.springbootapp.buildnowspringboot.exception;

public class RecruiterNameExistsException extends RuntimeException{
    public RecruiterNameExistsException(String message){
        super(message);
    }
}
