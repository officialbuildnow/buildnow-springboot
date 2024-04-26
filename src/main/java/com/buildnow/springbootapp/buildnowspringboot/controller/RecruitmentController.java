package com.buildnow.springbootapp.buildnowspringboot.controller;

import com.buildnow.springbootapp.buildnowspringboot.dto.RecruitingWorkType.RecruitingWorkTypeListDTO;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment.RecruitingWorkType;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment.Recruitment;
import com.buildnow.springbootapp.buildnowspringboot.service.Recruitment.RecruitingWorkTypeService;
import com.buildnow.springbootapp.buildnowspringboot.service.Recruitment.RecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/recruitment")
public class RecruitmentController {
    private final RecruitmentService recruitmentService;
    private final RecruitingWorkTypeService recruitingWorkTypeService;
    @PostMapping
    public ResponseEntity<Recruitment> createRecruitment(
            @RequestParam("deadLine") LocalDate deadLine,
            @RequestParam("threshold") Long threshold,
            Authentication authentication
            ){
        Recruitment newRecruitment = recruitmentService.createRecruitment(deadLine, threshold, authentication.getName());
        return new ResponseEntity<>(newRecruitment, HttpStatus.CREATED);
    }

    @PostMapping("/admin/worktype-init/{id}")
    public ResponseEntity<String> initializeWorkTypeAndPatent(
            @PathVariable("id") Long recruitmentId,
            @RequestBody RecruitingWorkTypeListDTO recruitingWorkTypeListDTO
            ){
        recruitingWorkTypeService.initialGradingOfShinHan(recruitmentId, recruitingWorkTypeListDTO);
        return new ResponseEntity<>("공종-면허 초기화 성공!", HttpStatus.CREATED);
    }

    @GetMapping("/applier/worktype-init/{id}")
    public ResponseEntity<List<RecruitingWorkType>> retrieveWorkTypeList(
            @PathVariable("id") Long recruitmentId
    ){
        List<RecruitingWorkType> res = recruitingWorkTypeService.retrieveRecruitingWorkType(recruitmentId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeExceptionHandler(RuntimeException ex){
        return new ResponseEntity<>("Error Occurred: " + ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
