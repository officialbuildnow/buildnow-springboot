package com.buildnow.springbootapp.buildnowspringboot.service;

import com.buildnow.springbootapp.buildnowspringboot.ENUM.RequiredLevelENUM;
import com.buildnow.springbootapp.buildnowspringboot.ENUM.UpperCategoryENUM;
import com.buildnow.springbootapp.buildnowspringboot.dto.ApplicationDocumentDTO;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.Applier;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.applierInfo.HandedOut;
import com.buildnow.springbootapp.buildnowspringboot.repository.ApplierRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.HandedOutRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HandedOutService {
    private final HandedOutRepository handedOutRepository;
    private final ApplierRepository applierRepository;
    @Transactional
    public HandedOut createHandedOut(String documentName, String documentURL, String applierName, UpperCategoryENUM upperCategoryENUM, RequiredLevelENUM requiredLevelENUM) throws RuntimeException {
        if(applierName == null) {
            throw new RuntimeException("applierName을 찾을 수 없습니다.");
        }
//        if(documentURL == null) {
//            throw new RuntimeException("documentURL이 첨부되지 않았습니다.");
//        }
        Applier applier = applierRepository.findByUsername(applierName);
        HandedOut newHandedOut = HandedOut.builder()
                .documentName(documentName)
                .documentUrl(documentURL)
                .build();

        if(handedOutRepository.existsByApplierAndDocumentName(applier, documentName)){
            throw new RuntimeException("이미 제출한 서류입니다.");
        }
        newHandedOut.setApplier(applier);
        newHandedOut.setUpperCategoryENUM(upperCategoryENUM);
        newHandedOut.setRequiredLevelENUM(requiredLevelENUM);
        return handedOutRepository.save(newHandedOut);
    }
}
