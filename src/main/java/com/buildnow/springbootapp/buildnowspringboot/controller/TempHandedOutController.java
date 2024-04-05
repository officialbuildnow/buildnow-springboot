package com.buildnow.springbootapp.buildnowspringboot.controller;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.TempHandedOut;
import com.buildnow.springbootapp.buildnowspringboot.repository.tempSave.TempHandedOutRepository;
import com.buildnow.springbootapp.buildnowspringboot.service.TempHandedOutService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/tempHanded")
public class TempHandedOutController {
    private final TempHandedOutRepository tempHandedOutRepository;
    private final TempHandedOutService tempHandedOutService;

    @GetMapping("/{id}")
    public ResponseEntity<TempHandedOut> findTempHandedOutByName(@PathVariable("id") Long applicationId, @RequestParam String documentName){
        TempHandedOut tempHandedOut = tempHandedOutService.findTempHandedOut(documentName, applicationId);
        return new ResponseEntity<>(tempHandedOut, HttpStatus.OK);
    }
}
