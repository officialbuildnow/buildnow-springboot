package com.buildnow.springbootapp.buildnowspringboot.service;

import com.buildnow.springbootapp.buildnowspringboot.dto.applicationEvaluation.ScoreResponseDTO;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.Recruiter;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.Application;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.ApplicationEvaluation;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment.Grading;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment.Recruitment;
import com.buildnow.springbootapp.buildnowspringboot.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationEvaluationService {
    private final ApplicationEvaluationRepository applicationEvaluationRepository;
    private final RecruitmentRepository recruitmentRepository;
    private final GradingRepository gradingRepository;
    private final ApplicationRepository applicationRepository;
    private final RecruiterRepository recruiterRepository;
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

    @Transactional
    public List<ScoreResponseDTO> retrieveScores(Long recruitmentId, Long applicationId, String recruiterName){
        List<ScoreResponseDTO> res = new ArrayList<>();
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(() -> new RuntimeException("해당하는 리크루트먼트가 없습니다."));
        if(!recruitment.getRecruiter().getUsername().equals(recruiterName)){
            throw new RuntimeException("해당 recruitment 관련 정보를 열람할 권한이 없습니다.");
        }
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("해당하는 어플리케이션이 없습니다."));
        List<Grading> gradingList = gradingRepository.findByRecruitment(recruitment);
        for(Grading grading : gradingList){
            List<ApplicationEvaluation> applicationEvaluationList = grading.getApplicationEvaluationList();
            for(ApplicationEvaluation evaluation : applicationEvaluationList){
                ScoreResponseDTO temp = new ScoreResponseDTO(
                        evaluation.getScore(),
                        grading.getUpperCategoryENUM(),
                        grading.getCategory()
                );
                res.add(temp);
            }
        }

        return res;
    }
}
