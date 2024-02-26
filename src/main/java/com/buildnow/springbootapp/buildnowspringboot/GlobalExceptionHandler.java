package com.buildnow.springbootapp.buildnowspringboot;

import com.buildnow.springbootapp.buildnowspringboot.exception.BusinessIdExistException;
import com.buildnow.springbootapp.buildnowspringboot.exception.RecruiterNameExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RecruiterNameExistsException.class)
    public ResponseEntity<ApiErrorResponse> handleRecruiterNameExistsException(RecruiterNameExistsException ex){
        ApiErrorResponse response = new ApiErrorResponse(HttpStatus.CONFLICT, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BusinessIdExistException.class)
    public ResponseEntity<?> handleBusinessIdExistsException(BusinessIdExistException ex){
        ApiErrorResponse response = new ApiErrorResponse(HttpStatus.CONFLICT, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}
