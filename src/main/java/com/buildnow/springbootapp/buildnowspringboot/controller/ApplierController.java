package com.buildnow.springbootapp.buildnowspringboot.controller;

import com.buildnow.springbootapp.buildnowspringboot.dto.ApplicationDocumentDTO;
import com.buildnow.springbootapp.buildnowspringboot.dto.ApplierSignUpDTO;
import com.buildnow.springbootapp.buildnowspringboot.dto.applier.EstDateUpdateDTO;
import com.buildnow.springbootapp.buildnowspringboot.dto.applier.HadAccidentUpdateDTO;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.Applier;
import com.buildnow.springbootapp.buildnowspringboot.service.*;
import com.buildnow.springbootapp.buildnowspringboot.service.demo.DemoApplierService;
import com.google.api.Http;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/applier")
public class ApplierController {
    private final ApplierService applierService;
    private final ApplicationByDocumentService applicationByDocumentService;
    private final DemoApplierService demoApplierService;
    @PatchMapping("/admin/update-hadAccident/{id}")
    public ResponseEntity<Applier> updateHadAccident(@PathVariable("id") Long applicationId, HadAccidentUpdateDTO hadAccidentUpdateDTO){
        Applier applier = applierService.updateApplierHadAccident(applicationId, hadAccidentUpdateDTO.getHadAccident());
        return new ResponseEntity<>(applier, HttpStatus.OK);
    }

    @PatchMapping("/admin/update-estDate/{id}")
    public ResponseEntity<Applier> updateEstDate(@PathVariable("id") Long applicationId, EstDateUpdateDTO estDateUpdateDTO){
        Applier applier = applierService.updateApplierEstDate(applicationId, estDateUpdateDTO.getEstDate());
        return new ResponseEntity<>(applier, HttpStatus.OK);
    }

    @PostMapping("/join")
    public ResponseEntity<Applier> createApplier(@Valid ApplierSignUpDTO applierSignUpDTO) throws Exception {
        log.info("진입!");
        Applier newApplier = applierService.createApplier(applierSignUpDTO);
        return new ResponseEntity<>(newApplier, HttpStatus.CREATED);
    }

    @PostMapping("/admin/duplicate/{id}")
    public ResponseEntity<String> duplicateApplier(@PathVariable("id") Long recruitmentId){
        demoApplierService.duplicateAppliers(recruitmentId);
        return new ResponseEntity<>("applier 복사 지원 완료", HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<Applier> retrieveApplier(Authentication authentication){
        Applier applier = applierService.retrieveApplierInfo(authentication.getName());
        return new ResponseEntity<>(applier, HttpStatus.OK);
    }

    @GetMapping("/recruiter/get-by-applicationId/{id}")
    public ResponseEntity<Applier> getApplierInfoByApplicationId(@PathVariable("id") Long applicationId,
                                                                 Authentication authentication){
        log.info(applicationId.toString());
        Applier applier = applierService.getApplierInfoByApplicationId(applicationId, authentication.getName());
        return new ResponseEntity<>(applier, HttpStatus.OK);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeExceptionHandler(RuntimeException ex){
        return new ResponseEntity<>("Error Occurred: " + ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
