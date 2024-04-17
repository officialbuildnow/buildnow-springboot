package com.buildnow.springbootapp.buildnowspringboot.controller;

import com.buildnow.springbootapp.buildnowspringboot.dto.License.LicensePostListDTO;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.applierInfo.License;
import com.buildnow.springbootapp.buildnowspringboot.repository.LicenseRepository;
import com.buildnow.springbootapp.buildnowspringboot.service.LicenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/license")
@RequiredArgsConstructor
public class LicenseController {
    private final LicenseService licenseService;

    @PostMapping("/admin/{id}")
    public ResponseEntity<List<License>> insertLicenseList(@PathVariable("id") Long applicationId, @RequestBody LicensePostListDTO licensePostListDTO){
        List<License> res = licenseService.insertLicenseList(applicationId, licensePostListDTO);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
}
