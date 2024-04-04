package com.buildnow.springbootapp.buildnowspringboot.controller;

import com.buildnow.springbootapp.buildnowspringboot.dto.ApplicationDocumentDTO;
import com.buildnow.springbootapp.buildnowspringboot.dto.ApplierSignUpDTO;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.Applier;
import com.buildnow.springbootapp.buildnowspringboot.service.*;
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

    @PostMapping("/join")
    public ResponseEntity<Applier> createApplier(@Valid ApplierSignUpDTO applierSignUpDTO) throws Exception {
        log.info("진입!");
        Applier newApplier = applierService.createApplier(applierSignUpDTO);
        return new ResponseEntity<>(newApplier, HttpStatus.CREATED);
    }
//    @PatchMapping("/application-document/{id}")
//    @ResponseBody
//    public ResponseEntity<String> registerApplierByDocument (ApplicationDocumentDTO applicationDocumentDTO, Authentication authentication, @PathVariable("id") Long recruitmentId) throws Exception {
//       applicationByDocumentService.registerApplierByDocument(applicationDocumentDTO.getDocumentName(),
//               applicationDocumentDTO.getDocumentURL(),
//               applicationDocumentDTO.getCorporateApplicationNum(),
//               applicationDocumentDTO.getCompanyPhoneNum(),
//               authentication.getName(),
//               applicationDocumentDTO.getPatent1Name(),
//               applicationDocumentDTO.getPatent2Name(),
//               applicationDocumentDTO.getPatent3Name(),
//               applicationDocumentDTO.getWorkTypeApplying(),
//               recruitmentId);
//        return new ResponseEntity<>("문서 입력 완료!",HttpStatus.CREATED);
//    }

    @GetMapping
    public ResponseEntity<Applier> retrieveApplier(Authentication authentication){
        Applier applier = applierService.retrieveApplierInfo(authentication.getName());
        return new ResponseEntity<>(applier, HttpStatus.OK);
    }

    @GetMapping("/recruiter/get-by-applicationId/{id}")
    public ResponseEntity<Applier> getApplierInfoByApplicationId(@PathVariable("id") Long applicationId,
                                                                 Authentication authentication){
        Applier applier = applierService.getApplierInfoByApplicationId(applicationId, authentication.getName());
        return new ResponseEntity<>(applier, HttpStatus.OK);
    }
}
