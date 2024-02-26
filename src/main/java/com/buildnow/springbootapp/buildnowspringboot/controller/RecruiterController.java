package com.buildnow.springbootapp.buildnowspringboot.controller;

import com.buildnow.springbootapp.buildnowspringboot.dto.RecruiterSignUpDTO;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.Recruiter;
import com.buildnow.springbootapp.buildnowspringboot.service.RecruiterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recruiter")
@RequiredArgsConstructor
public class RecruiterController {
    private final RecruiterService recruiterService;

    @PostMapping
    public ResponseEntity<Recruiter> createRecruiter(@Valid RecruiterSignUpDTO recruiterSignUpDTO) throws Exception {
        Recruiter newRecruiter = recruiterService.createRecruiter(recruiterSignUpDTO);
        return new ResponseEntity<>(newRecruiter, HttpStatus.CREATED);
    }
}
