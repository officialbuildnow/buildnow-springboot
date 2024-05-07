package com.buildnow.springbootapp.buildnowspringboot.service;

import com.buildnow.springbootapp.buildnowspringboot.dto.CustomUserDetails;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.Admin;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.Applier;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.Recruiter;
import com.buildnow.springbootapp.buildnowspringboot.repository.AdminRepository;
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
    private final AdminRepository adminRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Recruiter recruiter = recruiterRepository.findByUsername(username);
        if(recruiter != null){
            return new CustomUserDetails(recruiter);
        }
        Applier applier = applierRepository.findByUsername(username);
        if(applier != null){
            return new CustomUserDetails(applier);
        }
        Admin admin = adminRepository.findByUsername(username);
        if(admin != null){
            return new CustomUserDetails(admin);
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
