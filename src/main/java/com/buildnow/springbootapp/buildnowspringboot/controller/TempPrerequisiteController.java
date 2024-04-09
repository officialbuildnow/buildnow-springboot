package com.buildnow.springbootapp.buildnowspringboot.controller;

import com.buildnow.springbootapp.buildnowspringboot.dto.tempSave.TempPrerequisiteDTO;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.TempPrerequisite;
import com.buildnow.springbootapp.buildnowspringboot.repository.tempSave.TempPrerequisiteRepository;
import com.buildnow.springbootapp.buildnowspringboot.service.TempPrerequisiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/temp-prerequisite")
@RequiredArgsConstructor
public class TempPrerequisiteController {
    private final TempPrerequisiteService tempPrerequisiteService;
    @PostMapping("/admin/{id}")
    public ResponseEntity<TempPrerequisite> createTempPrerequisite(@PathVariable("id") Long applicationId,
                                                   TempPrerequisiteDTO tempPrerequisiteDTO){

        TempPrerequisite tempPrerequisite =  tempPrerequisiteService.createTempPrerequisite(
                tempPrerequisiteDTO.getPrerequisiteName(),
                tempPrerequisiteDTO.getIsPrerequisite(),
                tempPrerequisiteDTO.getWhyMidal(),
                applicationId
        );

        return new ResponseEntity<>(tempPrerequisite, HttpStatus.CREATED);
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<List<TempPrerequisite>> retrieveTempPrerequisite(
            @PathVariable("id") Long applicationId
    ){
        List<TempPrerequisite> tempPrerequisiteList = tempPrerequisiteService.retrieveTempPrerequisiteOfApplication(applicationId);
        return new ResponseEntity<>(tempPrerequisiteList, HttpStatus.OK);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeExceptionHandler(RuntimeException ex){
        return new ResponseEntity<>("Error Occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
