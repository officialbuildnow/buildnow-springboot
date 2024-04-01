package com.buildnow.springbootapp.buildnowspringboot;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.Admin;
import com.buildnow.springbootapp.buildnowspringboot.repository.AdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {
        String encodedPassword = passwordEncoder.encode("1234");
        Admin admin = Admin.builder()
                .username("jiho")
                .password(encodedPassword)
                .build();
        if(!adminRepository.existsByUsername(admin.getUsername())){
            adminRepository.save(admin);
        }
    }
}
