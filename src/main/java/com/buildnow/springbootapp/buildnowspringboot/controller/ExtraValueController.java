package com.buildnow.springbootapp.buildnowspringboot.controller;

import com.buildnow.springbootapp.buildnowspringboot.dto.extraValue.ExtraValueDTO;
import com.buildnow.springbootapp.buildnowspringboot.dto.extraValue.ExtraValueListDTO;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.applierInfo.ExtraValue;
import com.buildnow.springbootapp.buildnowspringboot.service.ExtraValueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/extra-value")
public class ExtraValueController {
    private final ExtraValueService extraValueService;

    @PostMapping("/admin/{id}")
    public ResponseEntity<List<ExtraValue>> createExtraValueList(@PathVariable("id") Long applicationId, @RequestBody ExtraValueListDTO extraValueListDTO){
        List<ExtraValue> res = extraValueService.createExtraValue(applicationId, extraValueListDTO);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<List<ExtraValue>> retrieveExtraValueList(@PathVariable("id") Long applicationId){
        List<ExtraValue> res = extraValueService.retrieveExtraValues(applicationId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PatchMapping("/admin/{id}")
    public ResponseEntity<String> updateExtraValue(@PathVariable("id") Long applicationId, @RequestBody ExtraValueListDTO extraValueListDTO){
        extraValueService.updateExtraValue(applicationId, extraValueListDTO);
        return new ResponseEntity<>("extra value 업데이트 완료!", HttpStatus.OK);
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<String> deleteExtraValue(@PathVariable("id") Long applicationId, @RequestParam("category") String category){
        extraValueService.removeExtraValue(applicationId, category);
        return new ResponseEntity<>("삭제 완료", HttpStatus.OK);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeExceptionHandler(RuntimeException ex){
        return new ResponseEntity<>("Error Occurred: " + ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
