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
    public ResponseEntity<List<ExtraValue>> createExtraValueList(@PathVariable("id") Long applierId, @RequestBody ExtraValueListDTO extraValueListDTO){
        List<ExtraValue> res = extraValueService.createExtraValue(applierId, extraValueListDTO);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<List<ExtraValue>> retrieveExtraValueList(@PathVariable("id") Long applierId){
        List<ExtraValue> res = extraValueService.retrieveExtraValues(applierId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PatchMapping("/admin/{id}")
    public ResponseEntity<ExtraValue> updateExtraValue(@PathVariable("id") Long applierId, ExtraValueDTO extraValueDTO){
        ExtraValue res = extraValueService.updateExtraValue(applierId, extraValueDTO);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<String> deleteExtraValue(@PathVariable("id") Long applierId, @RequestParam("category") String category){
        extraValueService.removeExtraValue(applierId, category);
        return new ResponseEntity<>("삭제 완료", HttpStatus.OK);
    }
}
