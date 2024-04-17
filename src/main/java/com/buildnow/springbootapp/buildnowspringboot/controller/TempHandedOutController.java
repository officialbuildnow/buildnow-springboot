package com.buildnow.springbootapp.buildnowspringboot.controller;

import com.buildnow.springbootapp.buildnowspringboot.dto.tempSave.TempHandedOutStatusDTO;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.TempHandedOut;
import com.buildnow.springbootapp.buildnowspringboot.repository.tempSave.TempHandedOutRepository;
import com.buildnow.springbootapp.buildnowspringboot.service.TempHandedOutService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/admin/update-status/{id}")
    public ResponseEntity<TempHandedOut> updateStatus(@PathVariable("id") Long applicationId, TempHandedOutStatusDTO tempHandedOutStatusDTO){
        TempHandedOut res = tempHandedOutService.updateTempHandedOutStatus(tempHandedOutStatusDTO.getDocumentName(), applicationId, tempHandedOutStatusDTO.getHandedOutVerifyingStatusENUM());
        log.info(String.valueOf(tempHandedOutStatusDTO.getHandedOutVerifyingStatusENUM()));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeExceptionHandler(RuntimeException ex){
        return new ResponseEntity<>("Error Occurred: " + ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
