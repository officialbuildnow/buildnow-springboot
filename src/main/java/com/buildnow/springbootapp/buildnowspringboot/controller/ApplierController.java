package com.buildnow.springbootapp.buildnowspringboot.controller;

import com.buildnow.springbootapp.buildnowspringboot.dto.ApplierSignUpDTO;
import com.buildnow.springbootapp.buildnowspringboot.dto.RecruiterSignUpDTO;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.Applier;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.Recruiter;
import com.buildnow.springbootapp.buildnowspringboot.service.ApplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/applier")
public class ApplierController {
    private final ApplierService applierService;
    @PostMapping
    public ResponseEntity<Applier> createApplier(@Valid ApplierSignUpDTO applierSignUpDTO) throws Exception {
        Applier newApplier = applierService.createApplier(applierSignUpDTO);
        return new ResponseEntity<>(newApplier, HttpStatus.CREATED);
    }
    @GetMapping("/data")
    @ResponseBody
    public String testApplier () {
        return "성공 applier";
    }
}
