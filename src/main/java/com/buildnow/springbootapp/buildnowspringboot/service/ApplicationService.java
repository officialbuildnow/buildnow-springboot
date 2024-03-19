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
    public Application createApplication(String workTypeApplying, String applierName, Long recruitmentId) throws RuntimeException {
        Applier applier = applierRepository.findByUsername(applierName);
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(() -> new RuntimeException("해당하는 리크루트먼트가 없습니다."));
        if(applicationRepository.existsByApplierAndRecruitment(applier, recruitment)){
            throw new RuntimeException("이미 지원한 내역이 있기 때문에 새로운 객체를 생성할 수 없습니다.");
        }
        Application newApplication = new Application(
                workTypeApplying
        );
        newApplication.setApplier(applier);
        newApplication.setRecruitment(recruitment);

        return applicationRepository.save(newApplication);
    }

    @Transactional
    public void deleteApplication(Long recruitmentId, String applierName) throws AuthenticationException {
        Applier applier = applierRepository.findByUsername(applierName);
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(() -> new RuntimeException("해당하는 리크루트먼트가 없습니다."));
        Application application = applicationRepository.findByApplierAndRecruitment(applier, recruitment);
        if(application == null){
            throw new RuntimeException("지우고자 하는 어플리케이션이 존재하지 않습니다.");
        }
        if(!application.getApplier().getUsername().equals(applierName)){
            throw new RuntimeException("삭제 권한이 없습니다.");
        }
        HandedOut applicationDoc = handedOutRepository.findByDocumentNameAndApplier("협력업체신청서", applier);
        handedOutRepository.delete(applicationDoc);
        applicationRepository.delete(application);
    }
}
