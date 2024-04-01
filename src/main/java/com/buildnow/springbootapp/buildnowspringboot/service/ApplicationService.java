package com.buildnow.springbootapp.buildnowspringboot.service;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.Applier;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.Application;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.applierInfo.HandedOut;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment.Recruitment;
import com.buildnow.springbootapp.buildnowspringboot.exception.NotFoundException;
import com.buildnow.springbootapp.buildnowspringboot.repository.ApplicationRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.ApplierRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.HandedOutRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.RecruitmentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public Application createApplication(String applierName, Long recruitmentId) throws RuntimeException {
        Applier applier = applierRepository.findByUsername(applierName);
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(() -> new RuntimeException("해당하는 리크루트먼트가 없습니다."));
        if(applicationRepository.existsByApplierAndRecruitment(applier, recruitment)){
            throw new RuntimeException("이미 지원한 내역이 있기 때문에 새로운 객체를 생성할 수 없습니다.");
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
    public void updateIsSubmitTrue(Long applicationId, String applierName){
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(()->new RuntimeException("애플리케이션이 존재하지 않습ㄴ디ㅏ."));
        Applier applier = applierRepository.findByUsername(applierName);

        if(!application.getApplier().getUsername().equals(applierName)){
            throw new RuntimeException("제출 권한이 없습니다.");
        }

        application.updateSubmitTrue();
    }
}
