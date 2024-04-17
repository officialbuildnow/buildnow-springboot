package com.buildnow.springbootapp.buildnowspringboot.service;

import com.buildnow.springbootapp.buildnowspringboot.dto.License.LicensePostDTO;
import com.buildnow.springbootapp.buildnowspringboot.dto.License.LicensePostListDTO;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.Applier;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.Application;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.applierInfo.License;
import com.buildnow.springbootapp.buildnowspringboot.repository.ApplicationRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.LicenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LicenseService {
    private final LicenseRepository licenseRepository;
    private final ApplicationRepository applicationRepository;

    @Transactional
    public List<License> insertLicenseList(Long applicationId, LicensePostListDTO licensePostListDTO){
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(()->new RuntimeException("해당하는 어플리케이션이 존재하지 않습니다."));
        Applier applier = application.getApplier();
        List<License> res = new ArrayList<>();
        for(LicensePostDTO license: licensePostListDTO.getLicensePostDTOList()){
            License newLicense = License.builder()
                    .licenseName(license.getLicenseName())
                    .licenseSeq(license.getLicenseSeq())
                    .licenseNum(license.getLicenseNum())
                    .licenseYear(license.getLicenseYear())
                    .capacityValue(license.getCapacityValue())
                    .build();
            res.add(newLicense);
            applier.addLicense(newLicense);
            licenseRepository.save(newLicense);
        }
        return res;
    }
}
