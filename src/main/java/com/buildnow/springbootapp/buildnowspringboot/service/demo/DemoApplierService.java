package com.buildnow.springbootapp.buildnowspringboot.service.demo;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.Applier;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.Application;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.ApplicationEvaluation;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.TempPrerequisite;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.TempSaved;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.applierInfo.ExtraValue;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.applierInfo.Finance;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.applierInfo.License;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment.Grading;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment.Recruitment;
import com.buildnow.springbootapp.buildnowspringboot.repository.*;
import com.buildnow.springbootapp.buildnowspringboot.repository.tempSave.TempPrerequisiteRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.tempSave.TempSavedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DemoApplierService {
    private final ApplierRepository applierRepository;
    private final ApplicationRepository applicationRepository;
    private final RecruitmentRepository recruitmentRepository;
    private final GradingRepository gradingRepository;
    private final TempPrerequisiteRepository tempPrerequisiteRepository;
    private final ApplicationEvaluationRepository applicationEvaluationRepository;
    private final TempSavedRepository tempSavedRepository;
    @Transactional
    public void duplicateAppliers(Long recruitmentId){
        Recruitment recruitment = recruitmentRepository.findById(2L)
                .orElseThrow(() -> new RuntimeException("recruitment가 없습니다."));
        Recruitment newRecruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(()->new RuntimeException("해당되는 recruitment가 없습니다."));
        if(!newRecruitment.getApplicationList().isEmpty()){
            throw new RuntimeException("비어있는 recruitment가 아닙니다. 새롭게 만들어낸 recruitment인지 확인하십시오.");
        }
        List<Grading> gradingList = recruitment.getGradingList();

        for(Grading grade : gradingList){
            Grading newGrading = Grading.builder()
                    .upperCategoryENUM(grade.getUpperCategoryENUM())
                    .perfectScore(grade.getPerfectScore())
                    .category(grade.getCategory())
                    .build();
            gradingRepository.save(newGrading);
            newRecruitment.addGrading(newGrading);
        }

        List<Application> applicationList = recruitment.getApplicationList();
        if(applicationList.isEmpty()) throw new RuntimeException("복사할 어플리케이션이 없습니다.");
        for(Application application : applicationList){
            if(!application.isAdminChecked()) continue;
            Applier applier = application.getApplier();
            List<TempPrerequisite> tempPrerequisiteList = application.getTempPrerequisiteList();

            Application newApplication = Application.builder()
                    .applier(applier)
                    .recruitment(newRecruitment)
                    .build();
            applicationRepository.save(newApplication);

            for (TempPrerequisite tempPrerequisite : tempPrerequisiteList) {
                TempPrerequisite newTempPrerequisite = TempPrerequisite.builder()
                        .isPrerequisite(tempPrerequisite.getIsPrerequisite())
                        .prerequisiteName(tempPrerequisite.getPrerequisiteName())
                        .whyMidal(tempPrerequisite.getWhyMidal())
                        .build();
                tempPrerequisiteRepository.save(newTempPrerequisite);
                newApplication.addTempPrerequisite(newTempPrerequisite);
            }

            List<ApplicationEvaluation> applicationEvaluationList = application.getApplicationEvaluationList();
            for(ApplicationEvaluation evaluation : applicationEvaluationList){
                ApplicationEvaluation newApplicationEvaluation = ApplicationEvaluation.builder()
                        .score(evaluation.getScore())
                        .build();
                applicationEvaluationRepository.save(newApplicationEvaluation);
                newApplicationEvaluation.setApplication(newApplication);
                Grading tempGrading = gradingRepository.findByRecruitmentAndCategory(newRecruitment, evaluation.getGrading().getCategory());
                if(tempGrading == null) throw new RuntimeException("해당하는 grading 엔티티가 없습니다.");
                newApplicationEvaluation.setGrading(tempGrading);
            }

            TempSaved tempSaved = application.getTempSaved();
            TempSaved newTempSaved = TempSaved.builder()
                    .licenseName(tempSaved.getLicenseName())
                    .companyAddress(tempSaved.getCompanyAddress())
                    .companyIntro(tempSaved.getCompanyIntro())
                    .companyPhoneNum(tempSaved.getCompanyPhoneNum())
                    .type(tempSaved.getType())
                    .corporateApplicationNum(tempSaved.getCorporateApplicationNum())
                    .workTypeApplying(tempSaved.getWorkTypeApplying())
                    .build();
            tempSavedRepository.save(newTempSaved);
            newApplication.setTempSaved(newTempSaved);
            newTempSaved.setApplication(newApplication);
            newApplication.updateApplicationFromTempSaved(application.getWorkTypeApplying());
            newApplication.updateIsAdminTrue();
        }

    }

}
