package com.buildnow.springbootapp.buildnowspringboot.controller;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.Application;
import com.buildnow.springbootapp.buildnowspringboot.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/application")
public class ApplicationController {
    private final ApplicationService applicationService;
    @PostMapping("/applier/{id}")
    public ResponseEntity<?> createApplication(@PathVariable("id") Long id, Authentication authentication) throws Exception {
        Application newApplication = applicationService.createApplication(authentication.getName(), id);
        return new ResponseEntity<>(newApplication, HttpStatus.CREATED);
    }

    @DeleteMapping("/applier/{id}")
    public ResponseEntity<String> deleteApplication(@PathVariable("id") Long id, Authentication authentication) throws AuthenticationException {
        applicationService.deleteApplication(id, authentication.getName());
        return new ResponseEntity<>("삭제 성공", HttpStatus.OK);
    }

    @GetMapping("/applier")
    public ResponseEntity<List<Application>> retrieveApplication(Authentication authentication)throws AuthenticationException {
        List<Application> res = applicationService.retrieveApplication(authentication.getName());
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/admin/all-application")
    public ResponseEntity<List<Application>> retrieveAllApplication(){
        List<Application> applicationList = applicationService.retrieveAllApplication();
        return new ResponseEntity<>(applicationList, HttpStatus.OK);
    }
    @PatchMapping("/applier/submit/{id}")
    public ResponseEntity<String> updateIsSubmitTrue(@PathVariable("id") Long applicationId, Authentication authentication){
        applicationService.updateIsSubmitTrue(applicationId, authentication.getName());
        return new ResponseEntity<>("제출 완료",HttpStatus.OK);
    }

    @PatchMapping("/admin/check-true/{id}")
    public ResponseEntity<String> updateIsAdminCheckedTrue(@PathVariable("id") Long applicationId){
        applicationService.updateIsAdminCheckedTrue(applicationId);
        return new ResponseEntity<>("어드민 검수 완료", HttpStatus.OK);
    }

    @PatchMapping("/admin/check-false/{id}")
    public ResponseEntity<String> updateIsAdminCheckedFalse(@PathVariable("id") Long applicationId){
        applicationService.updateIsAdminCheckedFalse(applicationId);
        return new ResponseEntity<>("어드민 검수 완료 철회", HttpStatus.OK);
    }

    @GetMapping("/recruiter/get-application-list/{id}")
    public ResponseEntity<List<Application>> retrieveApplicationByRecruitment(@PathVariable("id") Long recruitmentId, Authentication authentication){
        List<Application> applicationList = applicationService.retrieveApplicationByRecruitment(authentication.getName(), recruitmentId);
        return new ResponseEntity<>(applicationList, HttpStatus.OK);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeExceptionHandler(RuntimeException ex){
        return new ResponseEntity<>("Error Occurred: " + ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
