package com.buildnow.springbootapp.buildnowspringboot.service;

import com.buildnow.springbootapp.buildnowspringboot.dto.ApplicationDocumentDTO;
import com.buildnow.springbootapp.buildnowspringboot.dto.ApplierSignUpDTO;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.Applier;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.Recruiter;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.Application;
import com.buildnow.springbootapp.buildnowspringboot.exception.BusinessIdExistException;
import com.buildnow.springbootapp.buildnowspringboot.exception.NotFoundException;
import com.buildnow.springbootapp.buildnowspringboot.exception.UsernameExistsException;
import com.buildnow.springbootapp.buildnowspringboot.jwt.JWTUtil;
import com.buildnow.springbootapp.buildnowspringboot.repository.AdminRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.ApplicationRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.ApplierRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.RecruiterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApplierService {
    private final ApplierRepository applierRepository;
    private final RecruiterRepository recruiterRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationRepository applicationRepository;

    @Transactional
    public Applier createApplier(ApplierSignUpDTO applierSignUpDTO) {
        if(applierRepository.existsByUsername(applierSignUpDTO.getUsername()) || recruiterRepository.existsByUsername(applierSignUpDTO.getUsername()) || adminRepository.existsByUsername(applierSignUpDTO.getUsername())){
            throw new UsernameExistsException("이미 존재하는 아이디입니다.");
        } else if(applierRepository.existsByBusinessId(applierSignUpDTO.getBusinessId())){
            throw new BusinessIdExistException("이미 가입된 회사입니다.");
        }
        String encodedPassword = passwordEncoder.encode(applierSignUpDTO.getPassword());
        Applier newApplier = Applier.builder()
                .businessId(applierSignUpDTO.getBusinessId())
                .companyName(applierSignUpDTO.getCompanyName())
                .managerName(applierSignUpDTO.getManagerName())
                .managerPhoneNum(applierSignUpDTO.getManagerPhoneNum())
                .managerEmail(applierSignUpDTO.getManagerEmail())
                .username(applierSignUpDTO.getUsername())
                .password(encodedPassword)
                .build();
        return applierRepository.save(newApplier);
    }

    @Transactional
    public Applier retrieveApplierInfo(String applierName){
        return applierRepository.findByUsername(applierName);
    }

    @Transactional
    public Applier getApplierInfoByApplicationId(Long applicationId,
                                                 String recruiterName){
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(()->new RuntimeException("해당하는 application이 존재하지 않습니다."));
        Recruiter recruiter = recruiterRepository.findByUsername(recruiterName);
        if(!recruiter.equals(application.getRecruitment().getRecruiter())){
            throw new RuntimeException("권한이 없습니다.");
        }
        return application.getApplier();
    }

    @Transactional
    public Applier updateApplierEstDate(Long applicationId, LocalDate date){
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(()->new RuntimeException("해당하는 application이 존재하지 않습니다."));
        Applier applier = application.getApplier();
        applier.updateEstDate(date);
        return applier;
    }

    @Transactional
    public Applier updateApplierHadAccident(Long applicationId, Boolean hadAccident){
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(()->new RuntimeException("해당하는 application이 존재하지 않습니다."));
        Applier applier = application.getApplier();
        applier.setHadAccident(hadAccident);
        return applier;
    }
}
