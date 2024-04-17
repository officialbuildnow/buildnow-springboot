package com.buildnow.springbootapp.buildnowspringboot.service;

import com.buildnow.springbootapp.buildnowspringboot.ENUM.UpperCategoryENUM;
import com.buildnow.springbootapp.buildnowspringboot.dto.applicationEvaluation.ScoreResponseListDTO;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.Application;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.ApplicationEvaluation;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment.Grading;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment.Recruitment;
import com.buildnow.springbootapp.buildnowspringboot.repository.*;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.A;
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
    public void clearApplicationEvaluation(Long recruitmentId, Long applicationId) {
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(() -> new RuntimeException("해당하는 리크루트먼트가 없습니다."));
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("해당하는 어플리케이션이 없습니다."));

        // gradingList에 대한 직접적인 수정을 피하기 위해 복사본을 사용합니다.
        List<Grading> gradingList = new ArrayList<>(recruitment.getGradingList());

        // applicationEvaluationList에 대한 직접적인 수정을 피하기 위해 복사본을 사용합니다.
        List<ApplicationEvaluation> applicationEvaluationList = new ArrayList<>(application.getApplicationEvaluationList());

        for (Grading grading : gradingList) {
            for (ApplicationEvaluation evaluation : applicationEvaluationList) {
                grading.removeApplicationEvaluation(evaluation);
                application.removeApplicationEvaluation(evaluation);
                applicationEvaluationRepository.delete(evaluation);
            }
        }
    }

    @Transactional
    public ApplicationEvaluation createNewApplicationEvaluation(Long recruitmentId, Long applicationId, String categoryName, Long score){
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(() -> new RuntimeException("해당하는 리크루트먼트가 없습니다."));
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("해당하는 어플리케이션이 없습니다."));
        Grading grading = gradingRepository.findByRecruitmentAndCategory(recruitment, categoryName);
        List<ApplicationEvaluation> applicationEvaluationList = grading.getApplicationEvaluationList();
        List<ApplicationEvaluation> matchedList = new ArrayList<>();
        for(ApplicationEvaluation evaluation: applicationEvaluationList){
            if(evaluation.getApplication().equals(application)){
                matchedList.add(evaluation);
            }
        }
        if(matchedList.isEmpty()){
            if(grading.getPerfectScore() < score){
                throw new RuntimeException("만점보다 높은 점수를 입력할 수 없습니다.");
            }
            ApplicationEvaluation newApplicationEvaluation = ApplicationEvaluation.builder()
                    .score(score)
                    .build();
            newApplicationEvaluation.setApplication(application);
            newApplicationEvaluation.setGrading(grading);
            application.addApplicationEvaluation(newApplicationEvaluation);
            grading.addApplicationEvaluation(newApplicationEvaluation);
            return applicationEvaluationRepository.save(newApplicationEvaluation);
        }
        else{
            throw new RuntimeException("이미 해당 category에 점수가 들어가있습니다.");
        }
    }

    @Transactional
    public List<ScoreResponseListDTO> retrieveScores(Long recruitmentId, Long applicationId, String recruiterName){
        List<ScoreResponseListDTO> res = new ArrayList<>();

        ScoreResponseListDTO scoreResponseListDTO1 = new ScoreResponseListDTO();
        scoreResponseListDTO1.setUpperCategory(UpperCategoryENUM.BUSINESS);
        scoreResponseListDTO1.setUpperCategoryPerfectScore(0L);
        scoreResponseListDTO1.setUpperCategoryScore(0L);
        scoreResponseListDTO1.setScoreList(new ArrayList<>());
        res.add(scoreResponseListDTO1);

        ScoreResponseListDTO scoreResponseListDTO2 = new ScoreResponseListDTO();
        scoreResponseListDTO2.setUpperCategory(UpperCategoryENUM.FINANCE);
        scoreResponseListDTO2.setUpperCategoryPerfectScore(0L);
        scoreResponseListDTO2.setUpperCategoryScore(0L);
        scoreResponseListDTO2.setScoreList(new ArrayList<>());
        res.add(scoreResponseListDTO2);

        ScoreResponseListDTO scoreResponseListDTO3 = new ScoreResponseListDTO();
        scoreResponseListDTO3.setUpperCategory(UpperCategoryENUM.AUTHENTICATION);
        scoreResponseListDTO3.setUpperCategoryPerfectScore(0L);
        scoreResponseListDTO3.setUpperCategoryScore(0L);
        scoreResponseListDTO3.setScoreList(new ArrayList<>());
        res.add(scoreResponseListDTO3);

        ScoreResponseListDTO scoreResponseListDTO4 = new ScoreResponseListDTO();
        scoreResponseListDTO4.setUpperCategory(UpperCategoryENUM.PERFORMANCE);
        scoreResponseListDTO4.setUpperCategoryPerfectScore(0L);
        scoreResponseListDTO4.setUpperCategoryScore(0L);
        scoreResponseListDTO4.setScoreList(new ArrayList<>());
        res.add(scoreResponseListDTO4);

        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(() -> new RuntimeException("해당하는 리크루트먼트가 없습니다."));
        if(!recruitment.getRecruiter().getUsername().equals(recruiterName)){
            throw new RuntimeException("해당 recruitment 관련 정보를 열람할 권한이 없습니다.");
        }
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("해당하는 어플리케이션이 없습니다."));
        List<Grading> gradingList = gradingRepository.findByRecruitment(recruitment);
        for(Grading grading : gradingList){
            if(grading.getUpperCategoryENUM().equals(UpperCategoryENUM.BUSINESS)){
                ScoreResponseListDTO temp = res.get(0);
                Long updatePerfectScore = temp.getUpperCategoryPerfectScore() + grading.getPerfectScore();
                temp.setUpperCategoryPerfectScore(updatePerfectScore);
                List<ApplicationEvaluation> applicationEvaluationList = grading.getApplicationEvaluationList();
                for(ApplicationEvaluation evaluation : applicationEvaluationList){
                    if(evaluation.getApplication().equals(application)){
                        ScoreResponseListDTO.ScoreResponseDTO temp2 = new ScoreResponseListDTO.ScoreResponseDTO();
                        temp.setUpperCategoryScore(temp.getUpperCategoryScore() + evaluation.getScore());
                        temp2.setScore(evaluation.getScore());
                        temp2.setPerfectScore(grading.getPerfectScore());
                        temp2.setCategory(grading.getCategory());
                        temp.getScoreList().add(temp2);
                    }
                }
            }

            else if(grading.getUpperCategoryENUM().equals(UpperCategoryENUM.FINANCE)){
                ScoreResponseListDTO temp = res.get(1);
                Long updatePerfectScore = temp.getUpperCategoryPerfectScore() + grading.getPerfectScore();
                temp.setUpperCategoryPerfectScore(updatePerfectScore);
                List<ApplicationEvaluation> applicationEvaluationList = grading.getApplicationEvaluationList();
                for(ApplicationEvaluation evaluation : applicationEvaluationList){
                    if(evaluation.getApplication().equals(application)){
                        ScoreResponseListDTO.ScoreResponseDTO temp2 = new ScoreResponseListDTO.ScoreResponseDTO();
                        temp.setUpperCategoryScore(temp.getUpperCategoryScore() + evaluation.getScore());
                        temp2.setScore(evaluation.getScore());
                        temp2.setPerfectScore(grading.getPerfectScore());
                        temp2.setCategory(grading.getCategory());
                        temp.getScoreList().add(temp2);
                    }
                }
            }

            else if(grading.getUpperCategoryENUM().equals(UpperCategoryENUM.AUTHENTICATION)){
                ScoreResponseListDTO temp = res.get(2);
                Long updatePerfectScore = temp.getUpperCategoryPerfectScore() + grading.getPerfectScore();
                temp.setUpperCategoryPerfectScore(updatePerfectScore);
                List<ApplicationEvaluation> applicationEvaluationList = grading.getApplicationEvaluationList();
                for(ApplicationEvaluation evaluation : applicationEvaluationList){
                    if(evaluation.getApplication().equals(application)){
                        ScoreResponseListDTO.ScoreResponseDTO temp2 = new ScoreResponseListDTO.ScoreResponseDTO();
                        temp.setUpperCategoryScore(temp.getUpperCategoryScore() + evaluation.getScore());
                        temp2.setScore(evaluation.getScore());
                        temp2.setPerfectScore(grading.getPerfectScore());
                        temp2.setCategory(grading.getCategory());
                        temp.getScoreList().add(temp2);
                    }
                }
            }

            else if(grading.getUpperCategoryENUM().equals(UpperCategoryENUM.PERFORMANCE)){
                ScoreResponseListDTO temp = res.get(3);
                Long updatePerfectScore = temp.getUpperCategoryPerfectScore() + grading.getPerfectScore();
                temp.setUpperCategoryPerfectScore(updatePerfectScore);
                List<ApplicationEvaluation> applicationEvaluationList = grading.getApplicationEvaluationList();
                for(ApplicationEvaluation evaluation : applicationEvaluationList){
                    if(evaluation.getApplication().equals(application)){
                        ScoreResponseListDTO.ScoreResponseDTO temp2 = new ScoreResponseListDTO.ScoreResponseDTO();
                        temp.setUpperCategoryScore(temp.getUpperCategoryScore() + evaluation.getScore());
                        temp2.setScore(evaluation.getScore());
                        temp2.setPerfectScore(grading.getPerfectScore());
                        temp2.setCategory(grading.getCategory());
                        temp.getScoreList().add(temp2);
                    }
                }
            }
        }

        return res;
    }
}
