package com.buildnow.springbootapp.buildnowspringboot.service;

import com.buildnow.springbootapp.buildnowspringboot.dto.RecruiterSignUpDTO;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.Recruiter;
import com.buildnow.springbootapp.buildnowspringboot.exception.BusinessIdExistException;
import com.buildnow.springbootapp.buildnowspringboot.exception.NotFoundException;
import com.buildnow.springbootapp.buildnowspringboot.exception.UsernameExistsException;
import com.buildnow.springbootapp.buildnowspringboot.repository.AdminRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.ApplierRepository;
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
    private final ApplierRepository applierRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Recruiter createRecruiter(RecruiterSignUpDTO recruiterSignUpDTO) throws Exception{
        if(recruiterRepository.existsByUsername(recruiterSignUpDTO.getUsername()) || applierRepository.existsByUsername(recruiterSignUpDTO.getUsername()) || adminRepository.existsByUsername(recruiterSignUpDTO.getUsername())){
            throw new UsernameExistsException("이미 가입한 아이디입니다.");
        }else if(recruiterRepository.existsByBusinessId(recruiterSignUpDTO.getBusinessId())){
            throw new BusinessIdExistException("이미 가입된 회사입니다.");
        }
        String encodedPassword = passwordEncoder.encode(recruiterSignUpDTO.getPassword());
        Recruiter newRecruiter = Recruiter.builder()
                .username(recruiterSignUpDTO.getUsername())
                .password(encodedPassword)
                .businessId(recruiterSignUpDTO.getBusinessId())
                .managerName(recruiterSignUpDTO.getManagerName())
                .companyName(recruiterSignUpDTO.getCompanyName())
                .build();

        return recruiterRepository.save(newRecruiter);
    }

//    public Recruiter findRecruiterByRecruiterName(String recruiterName) throws Exception{
//        if(!recruiterRepository.existsByUsername(recruiterName)){
//            throw new NotFoundException("아이디를 다시 확인해주세요");
//        }
//
//    }
}
