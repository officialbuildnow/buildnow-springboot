package com.buildnow.springbootapp.buildnowspringboot.service;

import com.buildnow.springbootapp.buildnowspringboot.dto.TempSavingDTO;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.Application;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.TempHandedOut;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.TempSaved;
import com.buildnow.springbootapp.buildnowspringboot.repository.ApplicationRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.TempSavedRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TempSavedService {
    private final TempSavedRepository tempSavedRepository;
    private final ApplicationRepository applicationRepository;
    @Transactional
    public TempSaved saveOrUpdateTempSaved(Long applicationId, TempSavingDTO tempSavingDTO) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("해당하는 Application 이 없습니다"));

        TempSaved tempSaved = application.getTempSaved();

        if (tempSaved == null) {
            TempSaved newTempSaved = new TempSaved(
                    null,
                    tempSavingDTO.getCorporateApplication(),
                    tempSavingDTO.getCompanyPhoneNum(),
                    tempSavingDTO.getWorkTypeApplying(),
                    tempSavingDTO.getType(),
                    tempSavingDTO.getCompanyAddress(),
                    tempSavingDTO.getCompanyIntro(),
                    null,
                    null
            );
            newTempSaved.setApplication(application);
            return tempSavedRepository.save(newTempSaved);
        }
        tempSaved.updateTempSaved(
                tempSavingDTO.getCorporateApplication(),
                tempSavingDTO.getCompanyPhoneNum(),
                tempSavingDTO.getWorkTypeApplying(),
                tempSavingDTO.getType(),
                tempSavingDTO.getCompanyAddress(),
                tempSavingDTO.getCompanyIntro(),
                tempSavingDTO.getTempHandedOutList()
        );
        return tempSaved;
    }
}
