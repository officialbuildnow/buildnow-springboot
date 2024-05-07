package com.buildnow.springbootapp.buildnowspringboot.controller;

import com.buildnow.springbootapp.buildnowspringboot.dto.RecruiterSignUpDTO;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.Recruiter;
import com.buildnow.springbootapp.buildnowspringboot.service.RecruiterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Iterator;

@Controller
@RequestMapping("/recruiter")
@RequiredArgsConstructor
@Slf4j
public class RecruiterController {
    private final RecruiterService recruiterService;

    @PostMapping
    public ResponseEntity<Recruiter> createRecruiter(@Valid RecruiterSignUpDTO recruiterSignUpDTO) throws Exception {
        log.info(recruiterSignUpDTO.getUsername());
        Recruiter newRecruiter = recruiterService.createRecruiter(recruiterSignUpDTO);
        return new ResponseEntity<>(newRecruiter, HttpStatus.CREATED);
    }

    @GetMapping("/data")
    @ResponseBody
    public String checkAuth(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();
        return "Main Controller : "+name + " " + role;
    }

    @PatchMapping("/admin/update-logo/{id}")
    public ResponseEntity<String> updateLogo(@PathVariable("id") Long recruiterId, @RequestParam("logoUrl") String imageUrl){
        recruiterService.updateCompanyLogo(recruiterId, imageUrl);
        return new ResponseEntity<>("로고 이미지 업데이트 완료", HttpStatus.OK);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeExceptionHandler(RuntimeException ex){
        return new ResponseEntity<>("Error Occurred: " + ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
