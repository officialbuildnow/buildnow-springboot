package com.buildnow.springbootapp.buildnowspringboot.service;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.Application;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.applierInfo.HandedOut;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.applierInfo.Patent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Slf4j
@Service
@RequiredArgsConstructor
public class ApplicationByDocumentService {
    private final HandedOutService handedOutService;
    private final ApplierService applierService;
    private final PatentService patentService;
    private final ApplicationService applicationService;
    @Transactional
    public void registerApplierByDocument(String documentName,
                                          String documentURL,
                                          String corporateApplicationNum,
                                          String companyPhoneNum,
                                          String applierName,
                                          String patent1Name,
                                          String patent2Name,
                                          String patent3Name,
                                          String workTypeApplying,
                                          Long recruitmentId
    ) throws Exception {
        HandedOut applicationDocument = handedOutService.createHandedOut(
                documentName,
                documentURL,
                applierName
        );

        //applier 업데이트
        applierService.insertByApplicationDocument(
                corporateApplicationNum, companyPhoneNum, applierName
        );
        //특허 정보 저장
        if(patent1Name != null){
            Patent patent1 = patentService.createNewPatent(patent1Name, null, applierName);
        }
        if(patent2Name != null){
            Patent patent2 = patentService.createNewPatent(patent2Name, null, applierName);
        }
        if(patent3Name != null){
            Patent patent3 = patentService.createNewPatent(patent3Name, null, applierName);
        }

        //지원공종(주업종 저장) 이 때 같이 새로운 application 객체 생성
        Application newApplication = applicationService.createApplication(workTypeApplying, applierName, recruitmentId);
        log.info("문서 입력 완료!!");
    }
    //협력업체신청서 저장
}
