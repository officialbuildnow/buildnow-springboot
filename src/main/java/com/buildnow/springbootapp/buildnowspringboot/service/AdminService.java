package com.buildnow.springbootapp.buildnowspringboot.service;

import com.buildnow.springbootapp.buildnowspringboot.dto.AdminSignUpDTO;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.Admin;
import com.buildnow.springbootapp.buildnowspringboot.exception.UsernameExistsException;
import com.buildnow.springbootapp.buildnowspringboot.repository.AdminRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.ApplierRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.RecruiterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApplierRepository applierRepository;
    private final RecruiterRepository recruiterRepository;

    public Admin createAdmin(AdminSignUpDTO adminSignUpDTO){
        if (recruiterRepository.existsByUsername(adminSignUpDTO.getUsername()) || applierRepository.existsByUsername(adminSignUpDTO.getUsername()) || adminRepository.existsByUsername(adminSignUpDTO.getUsername())) {
            throw new UsernameExistsException("이미 가입한 아이디입니다.");
        }

        String encodedPassword = passwordEncoder.encode(adminSignUpDTO.getPassword());
        Admin newAdmin = new Admin(
                adminSignUpDTO.getUsername(),
                encodedPassword
        );

        return adminRepository.save(newAdmin);
    }


}
