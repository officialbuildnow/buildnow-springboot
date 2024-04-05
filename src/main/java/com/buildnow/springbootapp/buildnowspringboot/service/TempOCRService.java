package com.buildnow.springbootapp.buildnowspringboot.service;

import com.buildnow.springbootapp.buildnowspringboot.dto.tempSave.TempOCRDTO;
import com.buildnow.springbootapp.buildnowspringboot.dto.tempSave.TempOCRListDTO;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.Applier;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.Application;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.TempOCR;
import com.buildnow.springbootapp.buildnowspringboot.repository.ApplicationRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.ApplierRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.tempSave.TempOCRRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Slf4j
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
        if(tempOCRRepository.existsByApplicationAndCategory(application, category)){
            throw new RuntimeException("이미 저장된 항목입니다.");
        }
        TempOCR newTempOCR = TempOCR.builder()
                .category(category)
                .value(value)
                .build();
        application.addTempOCR(newTempOCR);
        return tempOCRRepository.save(newTempOCR);
    }

    @Transactional
    public List<TempOCR> retrieveMyTempOCRs(Long applicationId){
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(()-> new RuntimeException("해당하는 리크루트먼트가 없습니다."));

        return tempOCRRepository.findByApplication(application);
    }

    @Transactional
    public void updateTempOCR(Long applicationId, TempOCRListDTO tempOCRListDTO){
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(()->new RuntimeException("해당하는 application이 존재하지 않습니다."));
        List<TempOCR> tempOCRList = application.getTempOCRList();
        log.info(String.valueOf(tempOCRListDTO.getInfoList().size()));
        for(TempOCRDTO info: tempOCRListDTO.getInfoList()){
            log.info("for문 돌고 있음");
            for(TempOCR tempOCR : tempOCRList){
                log.info(tempOCR.getValue());

                if(info.getCategory().equals(tempOCR.getCategory())){
                    log.info("업데이트 시작");
                    tempOCR.updateValue(info.getValue());
                    log.info(tempOCR.getValue());
                }
            }
        }
    }

}
