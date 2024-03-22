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

@Controller
@RequiredArgsConstructor
@RequestMapping("/application")
public class ApplicationController {
    private final ApplicationService applicationService;
    @PostMapping("/{id}")
    public ResponseEntity<Application> createApplication(@PathVariable("id") Long id, @RequestParam("workTypeApplying") String workTypeApplying, Authentication authentication) throws Exception {
        Application newApplication = applicationService.createApplication(workTypeApplying, authentication.getName(), id);
        return new ResponseEntity<>(newApplication, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteApplication(@PathVariable("id") Long id, Authentication authentication) throws AuthenticationException {
        applicationService.deleteApplication(id, authentication.getName());
        return new ResponseEntity<>("삭제 성공", HttpStatus.OK);
    }
}
