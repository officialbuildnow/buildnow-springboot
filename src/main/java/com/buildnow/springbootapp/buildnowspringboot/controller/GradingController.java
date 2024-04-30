package com.buildnow.springbootapp.buildnowspringboot.controller;

import com.buildnow.springbootapp.buildnowspringboot.ENUM.UpperCategoryENUM;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.Recruiter;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.Application;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.ApplicationEvaluation;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment.Grading;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment.Recruitment;
import com.buildnow.springbootapp.buildnowspringboot.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/grading")
public class GradingController {
    private final RecruitmentRepository recruitmentRepository;
    private final GradingRepository gradingRepository;
    private final ApplicationEvaluationRepository applicationEvaluationRepository;
    private final RecruiterRepository recruiterRepository;
    @GetMapping("/admin/{id}")
    public ResponseEntity<List<Grading>> getGradingList(@PathVariable("id") Long recruitmentId){
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(()->new RuntimeException("존재하지 않는 recruitment 입니다."));
        List<Grading> gradingList = gradingRepository.findByRecruitment(recruitment);
        return new ResponseEntity<>(gradingList, HttpStatus.OK);
    }

    @GetMapping("/recruiter/{id}")
    public ResponseEntity<List<Grading>> getRecruiterList(@PathVariable("id") Long recruitmentId, Authentication authentication){
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(() -> new RuntimeException("해당하는 recruitment가 존재하지 않습니다."));
        if(!recruitment.getRecruiter().getUsername().equals(authentication.getName())){
            throw new RuntimeException("열람할 권한이 없습니다.");
        }
        return new ResponseEntity<>(recruitment.getGradingList(), HttpStatus.OK);
    }
    @Transactional
    @PostMapping("/admin/{id}")
    public ResponseEntity<List<Grading>> insertGradings(@PathVariable("id") Long recruitmentId){
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(()->new RuntimeException("존재하지 않는 recruitment 입니다."));
        List<Grading> currentGradingList = new ArrayList<>(recruitment.getGradingList());
        if(!currentGradingList.isEmpty()){
            for(Grading grading : currentGradingList){
                List<ApplicationEvaluation> evaluations = new ArrayList<>(grading.getApplicationEvaluationList());
                for(ApplicationEvaluation evaluation : evaluations){
                    Application application = evaluation.getApplication();
                    application.removeApplicationEvaluation(evaluation);
                    grading.removeApplicationEvaluation(evaluation);
                    applicationEvaluationRepository.delete(evaluation);
                }
                recruitment.removeGrading(grading);
                gradingRepository.delete(grading);
            }
        }
        List<Grading> res = new ArrayList<>();
        Grading grading1 = Grading.builder()
                .category("기술자수")
                .perfectScore(5L)
                .upperCategoryENUM(UpperCategoryENUM.BUSINESS)
                .build();
        grading1.setRecruitment(recruitment);
        recruitment.addGrading(grading1);
        gradingRepository.save(grading1);
        res.add(grading1);

        Grading grading2 = Grading.builder()
                .category("회사설립경과년수")
                .perfectScore(5L)
                .upperCategoryENUM(UpperCategoryENUM.BUSINESS)
                .build();
        grading2.setRecruitment(recruitment);
        recruitment.addGrading(grading2);
        gradingRepository.save(grading2);
        res.add(grading2);

        Grading grading4 = Grading.builder()
                .category("신용평가등급")
                .perfectScore(15L)
                .upperCategoryENUM(UpperCategoryENUM.FINANCE)
                .build();
        grading4.setRecruitment(recruitment);
        recruitment.addGrading(grading4);
        gradingRepository.save(grading4);
        res.add(grading4);

        Grading grading5 = Grading.builder()
                .category("현금흐름등급")
                .perfectScore(15L)
                .upperCategoryENUM(UpperCategoryENUM.FINANCE)
                .build();
        grading5.setRecruitment(recruitment);
        recruitment.addGrading(grading5);
        gradingRepository.save(grading5);
        res.add(grading5);

        Grading grading6 = Grading.builder()
                .category("부채비율")
                .perfectScore(10L)
                .upperCategoryENUM(UpperCategoryENUM.FINANCE)
                .build();
        grading6.setRecruitment(recruitment);
        recruitment.addGrading(grading6);
        gradingRepository.save(grading6);
        res.add(grading6);

        Grading grading7 = Grading.builder()
                .category("차입금의존도")
                .perfectScore(10L)
                .upperCategoryENUM(UpperCategoryENUM.FINANCE)
                .build();
        grading7.setRecruitment(recruitment);
        recruitment.addGrading(grading7);
        gradingRepository.save(grading7);
        res.add(grading7);




        Grading grading10 = Grading.builder()
                .category("직전년도시공능력평가액순위")
                .perfectScore(20L)
                .upperCategoryENUM(UpperCategoryENUM.PERFORMANCE)
                .build();
        grading10.setRecruitment(recruitment);
        recruitment.addGrading(grading10);
        gradingRepository.save(grading10);
        res.add(grading10);

        Grading grading11 = Grading.builder()
                .category("최근3년간공사실적")
                .perfectScore(20L)
                .upperCategoryENUM(UpperCategoryENUM.PERFORMANCE)
                .build();
        grading11.setRecruitment(recruitment);
        recruitment.addGrading(grading11);
        gradingRepository.save(grading11);
        res.add(grading11);

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeExceptionHandler(RuntimeException ex){
        return new ResponseEntity<>("Error Occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
