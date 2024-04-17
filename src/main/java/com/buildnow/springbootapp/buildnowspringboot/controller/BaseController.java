package com.buildnow.springbootapp.buildnowspringboot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class BaseController {
    @GetMapping
    public ResponseEntity<?> healthyCheck(){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
