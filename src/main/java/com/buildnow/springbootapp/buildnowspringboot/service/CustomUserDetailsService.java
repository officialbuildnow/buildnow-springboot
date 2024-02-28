package com.buildnow.springbootapp.buildnowspringboot.service;

import com.buildnow.springbootapp.buildnowspringboot.dto.CustomUserDetails;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.Applier;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.Recruiter;
import com.buildnow.springbootapp.buildnowspringboot.repository.ApplierRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.RecruiterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    private final RecruiterRepository recruiterRepository;
    private final ApplierRepository applierRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Recruiter recruiter = recruiterRepository.findByUsername(username);
        if(recruiter != null){
            log.info("나는 recruiter에요");
            return new CustomUserDetails(recruiter);
        }
        Applier applier = applierRepository.findByUsername(username);
        if(applier != null){
            return new CustomUserDetails(applier);
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
