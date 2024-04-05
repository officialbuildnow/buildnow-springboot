package com.buildnow.springbootapp.buildnowspringboot.service;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.Applier;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.Recruiter;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.Application;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.applierInfo.HandedOut;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment.Recruitment;
import com.buildnow.springbootapp.buildnowspringboot.exception.NotFoundException;
import com.buildnow.springbootapp.buildnowspringboot.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final ApplierRepository applierRepository;
    private final RecruitmentRepository recruitmentRepository;
    private final HandedOutRepository handedOutRepository;
    private final RecruiterRepository recruiterRepository;
    @Transactional
    public Application createApplication(String applierName, Long recruitmentId) throws RuntimeException {
        Applier applier = applierRepository.findByUsername(applierName);
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(() -> new RuntimeException("해당하는 리크루트먼트가 없습니다."));
        if(applicationRepository.existsByApplierAndRecruitment(applier, recruitment)){
            Application existingApplication = findExistingApplication(applierName, recruitmentId);
            throw new RuntimeException("이미 지원한 내역 (applicationId: " + existingApplication.getId() + ") 이 있기 때문에 새로운 객체를 생성할 수 없습니다.");
        }
        Application newApplication = Application.builder()
                .applier(applier)
                .recruitment(recruitment)
                .build();
        applier.addApplication(newApplication);
        recruitment.addApplication(newApplication);
        return applicationRepository.save(newApplication);
    }

    @Transactional
    public void deleteApplication(Long applicationId, String applierName) throws AuthenticationException {
        Applier applier = applierRepository.findByUsername(applierName);
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(()->new RuntimeException("애플리케이션이 존재하지 않습니다."));

        if(!application.getApplier().getUsername().equals(applierName)){
            throw new RuntimeException("삭제 권한이 없습니다.");
        }
        applicationRepository.delete(application);
    }

    @Transactional
    public List<Application> retrieveApplication( String applierName) {
        Applier applier = applierRepository.findByUsername(applierName);
        return applicationRepository.findByApplier(applier);
    }

    @Transactional
    public List<Application> retrieveAllApplication(){
        return applicationRepository.findAll();
    }

    @Transactional
    public Application findExistingApplication(String applierName, Long recruitmentId){
        Applier applier = applierRepository.findByUsername(applierName);
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(()->new RuntimeException("해당하는 recruitment가 존재하지 않습니다."));
        return applicationRepository.findByApplierAndRecruitment(applier, recruitment);
    }

    @Transactional
    public void updateIsSubmitTrue(Long applicationId, String applierName){
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(()->new RuntimeException("애플리케이션이 존재하지 않습니다."));
        Applier applier = applierRepository.findByUsername(applierName);

        if(!application.getApplier().getUsername().equals(applierName)){
            throw new RuntimeException("제출 권한이 없습니다.");
        }

        application.updateSubmitTrue();
    }

    @Transactional
    public void updateIsAdminCheckedTrue(Long applicationId){
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(()->new RuntimeException("애플리케이션이 존재하지 않습니다."));
        application.updateIsAdminTrue();
    }

    @Transactional
    public void updateIsAdminCheckedFalse(Long applicationId){
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(()->new RuntimeException("애플리케이션이 존재하지 않습니다."));
        application.updateIsAdminFalse();
    }

    @Transactional
    public List<Application> retrieveApplicationByRecruitment(String recruiterName, Long recruitmentId){
        Recruiter recruiter = recruiterRepository.findByUsername(recruiterName);
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(()->new RuntimeException("해당하는 recruitment가 없습니다."));
        if(!recruitment.getRecruiter().equals(recruiter)){
            throw new RuntimeException("해당 recruitment의 회사가 아니기 때문에 권한 없음.");
        }

       return applicationRepository.findByRecruitment(recruitment);
    }
}
