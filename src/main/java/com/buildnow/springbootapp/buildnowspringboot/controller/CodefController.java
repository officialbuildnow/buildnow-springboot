package com.buildnow.springbootapp.buildnowspringboot.controller;

import com.buildnow.springbootapp.buildnowspringboot.service.CodefService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("codef")
@RequiredArgsConstructor
public class CodefController {
//    private final CodefService codefService;
//    @GetMapping()
//    public ResponseEntity<?> registerUser(){
//        try{
//            String result = codefService.registerUser();
//            return ResponseEntity.ok(result);
//        }catch(Exception e){
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//
//        }
//    }
}
