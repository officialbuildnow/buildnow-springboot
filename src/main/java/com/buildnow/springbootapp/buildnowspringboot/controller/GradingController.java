package com.buildnow.springbootapp.buildnowspringboot.controller;

import com.buildnow.springbootapp.buildnowspringboot.ENUM.UpperCategoryENUM;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment.Grading;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment.Recruitment;
import com.buildnow.springbootapp.buildnowspringboot.repository.GradingRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/grading")
public class GradingController {
    private final RecruitmentRepository recruitmentRepository;
    private final GradingRepository gradingRepository;

    @GetMapping("/admin/{id}")
    public ResponseEntity<List<Grading>> getGradingList(@PathVariable("id") Long recruitmentId){
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(()->new RuntimeException("존재하지 않는 recruitment 입니다."));
        List<Grading> gradingList = gradingRepository.findByRecruitment(recruitment);
        return new ResponseEntity<>(gradingList, HttpStatus.OK);
    }
    @PostMapping("/admin/{id}")
    public ResponseEntity<List<Grading>> insertGradings(@PathVariable("id") Long recruitmentId){
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(()->new RuntimeException("존재하지 않는 recruitment 입니다."));
        List<Grading> res = new ArrayList<>();
        Grading grading1 = Grading.builder()
                .category("회사 설립 경과 년수")
                .perfectScore(5L)
                .upperCategoryENUM(UpperCategoryENUM.BUSINESS)
                .build();
        grading1.setRecruitment(recruitment);
        recruitment.addGrading(grading1);
        gradingRepository.save(grading1);
        res.add(grading1);

        Grading grading2 = Grading.builder()
                .category("지방 업체 (서울 경기 외) 여부")
                .perfectScore(5L)
                .upperCategoryENUM(UpperCategoryENUM.BUSINESS)
                .build();
        grading2.setRecruitment(recruitment);
        recruitment.addGrading(grading2);
        gradingRepository.save(grading2);
        res.add(grading2);

        Grading grading3 = Grading.builder()
                .category("산재 발생 여부")
                .perfectScore(5L)
                .upperCategoryENUM(UpperCategoryENUM.BUSINESS)
                .build();
        grading3.setRecruitment(recruitment);
        recruitment.addGrading(grading3);
        gradingRepository.save(grading3);
        res.add(grading3);

        Grading grading4 = Grading.builder()
                .category("신용 등급")
                .perfectScore(10L)
                .upperCategoryENUM(UpperCategoryENUM.FINANCE)
                .build();
        grading4.setRecruitment(recruitment);
        recruitment.addGrading(grading4);
        gradingRepository.save(grading4);
        res.add(grading4);

        Grading grading5 = Grading.builder()
                .category("현금흐름 등급")
                .perfectScore(10L)
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
                .category("차입금 의존도")
                .perfectScore(10L)
                .upperCategoryENUM(UpperCategoryENUM.FINANCE)
                .build();
        grading7.setRecruitment(recruitment);
        recruitment.addGrading(grading7);
        gradingRepository.save(grading7);
        res.add(grading7);

        Grading grading8 = Grading.builder()
                .category("ESG 인증 및 평가")
                .perfectScore(5L)
                .upperCategoryENUM(UpperCategoryENUM.AUTHENTICATION)
                .build();
        grading8.setRecruitment(recruitment);
        recruitment.addGrading(grading8);
        gradingRepository.save(grading8);
        res.add(grading8);

        Grading grading9 = Grading.builder()
                .category("ISO 인증서 보유 여부")
                .perfectScore(10L)
                .upperCategoryENUM(UpperCategoryENUM.AUTHENTICATION)
                .build();
        grading9.setRecruitment(recruitment);
        recruitment.addGrading(grading9);
        gradingRepository.save(grading9);
        res.add(grading9);

        Grading grading10 = Grading.builder()
                .category("시공능력 평가액 순위(%)")
                .perfectScore(15L)
                .upperCategoryENUM(UpperCategoryENUM.PERFORMANCE)
                .build();
        grading10.setRecruitment(recruitment);
        recruitment.addGrading(grading10);
        gradingRepository.save(grading10);
        res.add(grading10);

        Grading grading11 = Grading.builder()
                .category("최근 3년간 공사 실적")
                .perfectScore(15L)
                .upperCategoryENUM(UpperCategoryENUM.PERFORMANCE)
                .build();
        grading11.setRecruitment(recruitment);
        recruitment.addGrading(grading11);
        gradingRepository.save(grading11);
        res.add(grading11);

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
}
