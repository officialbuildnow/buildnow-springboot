package com.buildnow.springbootapp.buildnowspringboot.exception;

public class BusinessIdExistException extends RuntimeException{
    public BusinessIdExistException(String message){
        super(message);
    }
}
