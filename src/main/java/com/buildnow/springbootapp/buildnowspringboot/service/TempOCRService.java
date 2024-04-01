package com.buildnow.springbootapp.buildnowspringboot.service;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.Applier;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.Application;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.TempOCR;
import com.buildnow.springbootapp.buildnowspringboot.repository.ApplicationRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.ApplierRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.TempOCRRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TempOCRService {
    private final TempOCRRepository tempOCRRepository;
    private final ApplierRepository applierRepository;
    private final ApplicationRepository applicationRepository;
    @Transactional
    public TempOCR createTempOCR(String applierName,
                                 Long applicationId,
                                 String category,
                                 String value){
        Applier applier = applierRepository.findByUsername(applierName);
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(()-> new RuntimeException("해당하는 리크루트먼트가 없습니다."));
        if(!applier.equals(application.getApplier())){
            throw new RuntimeException("권한이 없습니다.");
        }
        TempOCR newTempOCR = TempOCR.builder()
                .category(category)
                .value(category)
                .build();
        application.addTempOCR(newTempOCR);
        return tempOCRRepository.save(newTempOCR);
    }
}
