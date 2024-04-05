package com.buildnow.springbootapp.buildnowspringboot.controller;

import com.buildnow.springbootapp.buildnowspringboot.dto.TempSavingDTO;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.TempHandedOut;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.TempSaved;
import com.buildnow.springbootapp.buildnowspringboot.service.TempSavedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/tempsave")
@RequiredArgsConstructor
public class TempSaveController {

    private final TempSavedService tempSavedService;
    @GetMapping("/applier/{id}")
    public ResponseEntity<TempSaved> getTempSaved(@PathVariable("id") Long applicationId, Authentication authentication){
        TempSaved tempSaved = tempSavedService.getTempSaved(applicationId, authentication.getName());
        return new ResponseEntity<>(tempSaved, HttpStatus.OK);
    }
    @PatchMapping(value = "/applier/{id}")
    public ResponseEntity<TempSaved> saveOrUpdateTempSave(@PathVariable("id") Long applicationId, TempSavingDTO tempSavingDTO){
        TempSaved tempSaved = tempSavedService.saveOrUpdateTempSaved(applicationId, tempSavingDTO);
        return new ResponseEntity<>(tempSaved, HttpStatus.OK);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeExceptionHandler(RuntimeException ex){
        return new ResponseEntity<>("Error Occurred: " + ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
