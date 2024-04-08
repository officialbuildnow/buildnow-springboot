package com.buildnow.springbootapp.buildnowspringboot.service;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.Application;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.ApplicationEvaluation;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment.Grading;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment.Recruitment;
import com.buildnow.springbootapp.buildnowspringboot.repository.ApplicationEvaluationRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.ApplicationRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.GradingRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApplicationEvaluationService {
    private final ApplicationEvaluationRepository applicationEvaluationRepository;
    private final RecruitmentRepository recruitmentRepository;
    private final GradingRepository gradingRepository;
    private final ApplicationRepository applicationRepository;
    @Transactional
    public ApplicationEvaluation createNewApplicationEvaluation(Long recruitmentId, Long applicationId, String categoryName, Long score){
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(() -> new RuntimeException("해당하는 리크루트먼트가 없습니다."));
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("해당하는 어플리케이션이 없습니다."));
        Grading grading = gradingRepository.findByRecruitmentAndCategory(recruitment, categoryName);
        ApplicationEvaluation newApplicationEvaluation = ApplicationEvaluation.builder()
                .score(score)
                .build();
        newApplicationEvaluation.setApplication(application);
        newApplicationEvaluation.setGrading(grading);
        application.addApplicationEvaluation(newApplicationEvaluation);
        grading.addApplicationEvaluation(newApplicationEvaluation);
        return applicationEvaluationRepository.save(newApplicationEvaluation);
    }
}
