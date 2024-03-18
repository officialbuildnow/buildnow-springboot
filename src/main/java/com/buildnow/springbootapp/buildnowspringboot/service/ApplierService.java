package com.buildnow.springbootapp.buildnowspringboot.service;

import com.buildnow.springbootapp.buildnowspringboot.dto.ApplicationDocumentDTO;
import com.buildnow.springbootapp.buildnowspringboot.dto.ApplierSignUpDTO;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.Applier;
import com.buildnow.springbootapp.buildnowspringboot.exception.BusinessIdExistException;
import com.buildnow.springbootapp.buildnowspringboot.exception.UsernameExistsException;
import com.buildnow.springbootapp.buildnowspringboot.repository.AdminRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.ApplierRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.RecruiterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApplierService {
    private final ApplierRepository applierRepository;
    private final RecruiterRepository recruiterRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public Applier createApplier(ApplierSignUpDTO applierSignUpDTO) {
        if(applierRepository.existsByUsername(applierSignUpDTO.getUsername()) || recruiterRepository.existsByUsername(applierSignUpDTO.getUsername()) || adminRepository.existsByUsername(applierSignUpDTO.getUsername())){
            throw new UsernameExistsException("이미 존재하는 아이디입니다.");
        } else if(applierRepository.existsByBusinessId(applierSignUpDTO.getBusinessId())){
            throw new BusinessIdExistException("이미 가입된 회사입니다.");
        }
        String encodedPassword = passwordEncoder.encode(applierSignUpDTO.getPassword());
        Applier newApplier = new Applier(
                applierSignUpDTO.getBusinessId(),
                applierSignUpDTO.getManagerName(),
                applierSignUpDTO.getManagerPhoneNum(),
                applierSignUpDTO.getManagerEmail(),
                applierSignUpDTO.getUsername(),
                encodedPassword
        );
        return applierRepository.save(newApplier);
    }

//    @Transactional
//    public Applier insertByApplicationDocument(ApplicationDocumentDTO applicationDocumentDTO, Applier applier){
//
//    }

}
