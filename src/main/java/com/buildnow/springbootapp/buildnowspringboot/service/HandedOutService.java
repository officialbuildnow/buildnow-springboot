package com.buildnow.springbootapp.buildnowspringboot.service;

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
    public HandedOut createHandedOut(String documentName, String documentURL, String applierName) throws RuntimeException {
        if(applierName == null) {
            throw new RuntimeException("applierName을 찾을 수 없습니다.");
        }
        if(documentURL == null) {
            throw new RuntimeException("documentURL이 첨부되지 않았습니다.");
        }
        Applier applier = applierRepository.findByUsername(applierName);
        HandedOut newHandedOut = new HandedOut(
                documentName,
                documentURL
        );

        if(handedOutRepository.existsByApplierAndDocumentName(applier, documentName)){
            throw new RuntimeException("이미 제출한 서류입니다.");
        }
        newHandedOut.setApplier(applier);
        return handedOutRepository.save(newHandedOut);
    }
}
