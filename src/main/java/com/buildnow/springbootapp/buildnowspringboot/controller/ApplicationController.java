package com.buildnow.springbootapp.buildnowspringboot.controller;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.Application;
import com.buildnow.springbootapp.buildnowspringboot.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/application")
public class ApplicationController {
    private final ApplicationService applicationService;
    @PostMapping("/{id}")
    public ResponseEntity<?> createApplication(@PathVariable("id") Long id, Authentication authentication) throws Exception {
        Application newApplication = applicationService.createApplication(authentication.getName(), id);
        return new ResponseEntity<>(newApplication, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteApplication(@PathVariable("id") Long id, Authentication authentication) throws AuthenticationException {
        applicationService.deleteApplication(id, authentication.getName());
        return new ResponseEntity<>("삭제 성공", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Application>> retrieveApplication(Authentication authentication)throws AuthenticationException {
        List<Application> res = applicationService.retrieveApplication(authentication.getName());
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PatchMapping("/submit/{id}")
    public ResponseEntity<String> updateIsSubmitTrue(@PathVariable("id") Long applicationId, Authentication authentication){
        applicationService.updateIsSubmitTrue(applicationId, authentication.getName());
        return new ResponseEntity<>("제출 완료",HttpStatus.OK);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeExceptionHandler(RuntimeException ex){
        return new ResponseEntity<>("Error Occurred: " + ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
