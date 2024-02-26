package com.buildnow.springbootapp.buildnowspringboot.service;

import com.buildnow.springbootapp.buildnowspringboot.dto.RecruiterSignUpDTO;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.Recruiter;
import com.buildnow.springbootapp.buildnowspringboot.exception.BusinessIdExistException;
import com.buildnow.springbootapp.buildnowspringboot.exception.RecruiterNameExistsException;
import com.buildnow.springbootapp.buildnowspringboot.repository.RecruiterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.DuplicateFormatFlagsException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecruiterService {
    private final RecruiterRepository recruiterRepository;

    public Recruiter createRecruiter(RecruiterSignUpDTO recruiterSignUpDTO) throws Exception{
        if(recruiterRepository.existsByRecruiterName(recruiterSignUpDTO.getRecruiterName())){
            throw new RecruiterNameExistsException("이미 가입한 아이디입니다.");
        }else if(recruiterRepository.existsByBusinessId(recruiterSignUpDTO.getBusinessId())){
            throw new BusinessIdExistException("이미 가입된 회사입니다.");
        }
        Recruiter newRecruiter = new Recruiter(
                recruiterSignUpDTO.getRecruiterName(),
                recruiterSignUpDTO.getPassword(),
                recruiterSignUpDTO.getBusinessId(),
                recruiterSignUpDTO.getManagerName(),
                recruiterSignUpDTO.getCompanyName()
        );

        return recruiterRepository.save(newRecruiter);
    }
}
