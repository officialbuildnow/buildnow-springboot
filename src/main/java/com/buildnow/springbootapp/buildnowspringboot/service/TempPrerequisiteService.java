package com.buildnow.springbootapp.buildnowspringboot.service;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.Application;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.TempPrerequisite;
import com.buildnow.springbootapp.buildnowspringboot.repository.ApplicationRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.tempSave.TempPrerequisiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TempPrerequisiteService {
    private final TempPrerequisiteRepository tempPrerequisiteRepository;
    private final ApplicationRepository applicationRepository;
    @Transactional
    public TempPrerequisite createTempPrerequisite(String prerequisiteName,
                                                   Boolean isPrerequisite,
                                                   String whyMidal,
                                                   Long applicationId){
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(()-> new RuntimeException("해당하는 application이 존재하지 않습니다."));

        TempPrerequisite newTempPrerequisite = TempPrerequisite.builder()
                .prerequisiteName(prerequisiteName)
                .isPrerequisite(isPrerequisite)
                .whyMidal(whyMidal)
                .build();
        application.addTempPrerequisite(newTempPrerequisite);
        return tempPrerequisiteRepository.save(newTempPrerequisite);
    }

    @Transactional
    public List<TempPrerequisite> retrieveTempPrerequisiteOfApplication(
            Long applicationId
    ){
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("해당하는 application이 존재하지 않습니다."));

        return application.getTempPrerequisiteList();
    }
}
