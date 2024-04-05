package com.buildnow.springbootapp.buildnowspringboot.service;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.Application;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.TempHandedOut;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.TempSaved;
import com.buildnow.springbootapp.buildnowspringboot.repository.ApplicationRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.tempSave.TempHandedOutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TempHandedOutService {
    private final TempHandedOutRepository tempHandedOutRepository;
    private final ApplicationRepository applicationRepository;
    @Transactional
    public TempHandedOut findTempHandedOut(String documentName, Long applicationId){
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(()->new RuntimeException("해당하는 application이 존재하지 않습니다."));
        TempSaved tempSaved = application.getTempSaved();
        return tempHandedOutRepository.findByDocumentNameAndTempSaved(documentName, tempSaved);
    }
}
