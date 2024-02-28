package com.buildnow.springbootapp.buildnowspringboot.service;

import com.buildnow.springbootapp.buildnowspringboot.dto.RecruiterSignUpDTO;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.Recruiter;
import com.buildnow.springbootapp.buildnowspringboot.exception.BusinessIdExistException;
import com.buildnow.springbootapp.buildnowspringboot.exception.NotFoundException;
import com.buildnow.springbootapp.buildnowspringboot.exception.RecruiterNameExistsException;
import com.buildnow.springbootapp.buildnowspringboot.repository.RecruiterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.DuplicateFormatFlagsException;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class RecruiterService {

    private final RecruiterRepository recruiterRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Recruiter createRecruiter(RecruiterSignUpDTO recruiterSignUpDTO) throws Exception{
        if(recruiterRepository.existsByUsername(recruiterSignUpDTO.getUsername())){
            throw new RecruiterNameExistsException("이미 가입한 아이디입니다.");
        }else if(recruiterRepository.existsByBusinessId(recruiterSignUpDTO.getBusinessId())){
            throw new BusinessIdExistException("이미 가입된 회사입니다.");
        }
        String encodedPassword = passwordEncoder.encode(recruiterSignUpDTO.getPassword());
        Recruiter newRecruiter = new Recruiter(
                recruiterSignUpDTO.getUsername(),
                encodedPassword,
                recruiterSignUpDTO.getBusinessId(),
                recruiterSignUpDTO.getManagerName(),
                recruiterSignUpDTO.getCompanyName()
        );

        return recruiterRepository.save(newRecruiter);
    }

//    public Recruiter findRecruiterByRecruiterName(String recruiterName) throws Exception{
//        if(!recruiterRepository.existsByUsername(recruiterName)){
//            throw new NotFoundException("아이디를 다시 확인해주세요");
//        }
//
//    }
}
