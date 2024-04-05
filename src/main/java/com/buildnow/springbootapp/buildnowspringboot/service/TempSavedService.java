package com.buildnow.springbootapp.buildnowspringboot.service;

import com.buildnow.springbootapp.buildnowspringboot.dto.TempSavedResponseDTO;
import com.buildnow.springbootapp.buildnowspringboot.dto.TempSavingDTO;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.Applier;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.Application;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.TempHandedOut;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.TempSaved;
import com.buildnow.springbootapp.buildnowspringboot.repository.ApplicationRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.ApplierRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.TempSavedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;
@Slf4j
@Service
@RequiredArgsConstructor
public class TempSavedService {
    private final TempSavedRepository tempSavedRepository;
    private final ApplicationRepository applicationRepository;
    private final ApplierRepository applierRepository;
    @Transactional
    public TempSaved getTempSaved(Long applicationId, String applierName){
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("해당하는 Application 이 없습니다"));

        Applier applier = applierRepository.findByUsername(applierName);
        if(!application.getApplier().equals(applier)){
            throw new RuntimeException("해당 application의 임시저장을 열람할 권한이 없습니다.");
        }

        return application.getTempSaved();
    }
    @Transactional
    public TempSaved saveOrUpdateTempSaved(Long applicationId, TempSavingDTO tempSavingDTO) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("해당하는 Application 이 없습니다"));

        TempSaved tempSaved = application.getTempSaved();

        if (tempSaved == null) {
            log.debug(tempSavingDTO.getCorporateApplication());
            TempSaved newTempSaved = TempSaved.builder()
                    .corporateApplicationNum(tempSavingDTO.getCorporateApplication())
                    .companyPhoneNum(tempSavingDTO.getCompanyPhoneNum())
                    .workTypeApplying(tempSavingDTO.getWorkTypeApplying())
                    .type(tempSavingDTO.getType())
                    .companyAddress(tempSavingDTO.getCompanyAddress())
                    .companyIntro(tempSavingDTO.getCompanyIntro())
                    .build();

            newTempSaved.setApplication(application);
            application.setTempSaved(newTempSaved);
            return tempSavedRepository.save(newTempSaved);
        }
        else{
            tempSaved.updateTempSaved(
                    tempSavingDTO.getCorporateApplication(),
                    tempSavingDTO.getCompanyPhoneNum(),
                    tempSavingDTO.getWorkTypeApplying(),
                    tempSavingDTO.getType(),
                    tempSavingDTO.getCompanyAddress(),
                    tempSavingDTO.getCompanyIntro(),
                    tempSavingDTO.getTempHandedOutList()
            );
        }
        return tempSaved;
    }
}
