package com.buildnow.springbootapp.buildnowspringboot.controller;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment.Recruitment;
import com.buildnow.springbootapp.buildnowspringboot.service.RecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@RequestMapping("/recruitment")
public class RecruitmentController {
    private final RecruitmentService recruitmentService;

    @PostMapping
    public ResponseEntity<Recruitment> createRecruitment(
            @RequestParam("deadLine") LocalDate deadLine,
            @RequestParam("threshold") Long threshold,
            Authentication authentication
            ){
        Recruitment newRecruitment = recruitmentService.createRecruitment(deadLine, threshold, authentication.getName());
        return new ResponseEntity<>(newRecruitment, HttpStatus.CREATED);
    }
}
