package com.buildnow.springbootapp.buildnowspringboot.service;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.TempHandedOut;
import com.buildnow.springbootapp.buildnowspringboot.repository.TempHandedOutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TempHandedOutService {
    private final TempHandedOutRepository tempHandedOutRepository;

    @Transactional
    public TempHandedOut findTempHandedOut(String documentName){
        return tempHandedOutRepository.findByDocumentName(documentName);
    }
}
