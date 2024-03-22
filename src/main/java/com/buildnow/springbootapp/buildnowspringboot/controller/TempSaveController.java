package com.buildnow.springbootapp.buildnowspringboot.controller;

import com.buildnow.springbootapp.buildnowspringboot.dto.TempSavingDTO;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.TempSaved;
import com.buildnow.springbootapp.buildnowspringboot.service.TempSavedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/tempsave")
@RequiredArgsConstructor
public class TempSaveController {

    private final TempSavedService tempSavedService;
    @PostMapping("/{id}")
    public ResponseEntity<TempSaved> saveOrUpdateTempSave(@PathVariable("id") Long applicationId, @ModelAttribute TempSavingDTO tempSavingDTO){
        TempSaved tempSaved = tempSavedService.saveOrUpdateTempSaved(applicationId, tempSavingDTO);
        return new ResponseEntity<>(tempSaved, HttpStatus.OK);
    }
}
