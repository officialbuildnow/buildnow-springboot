package com.buildnow.springbootapp.buildnowspringboot.service;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.Applier;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.*;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.applierInfo.Finance;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.applierInfo.HandedOut;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.applierInfo.License;
import com.buildnow.springbootapp.buildnowspringboot.repository.ApplicationRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.FinanceRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.HandedOutRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.LicenseRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.tempSave.TempOCRRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.tempSave.TempPrerequisiteRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.tempSave.TempSavedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConvertTempToRealService {
    private final TempSavedRepository tempSavedRepository;
    private final TempOCRRepository tempOCRRepository;
    private final TempPrerequisiteRepository tempPrerequisiteRepository;
    private final ApplicationRepository applicationRepository;
    private final HandedOutRepository handedOutRepository;
    private final FinanceRepository financeRepository;
    private final LicenseRepository licenseRepository;
    @Transactional
    public void convertTempToReal(Long applicationId){
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(()->new RuntimeException("해당하는 application이 존재하지 않습니다."));
        TempSaved tempSaved = application.getTempSaved();
        List<TempHandedOut> tempHandedOutList = tempSaved.getTempHandedOutList();
        List<TempOCR> tempOCRList = application.getTempOCRList();

        Applier applier = application.getApplier();

        //Handed out 저장
        for(TempHandedOut tempHandedOut : tempHandedOutList){
            HandedOut newHandedOut = HandedOut.builder()
                    .documentName(tempHandedOut.getDocumentName())
                    .documentUrl(tempHandedOut.getDocumentUrl())
                    .requiredLevelENUM(tempHandedOut.getRequiredLevelENUM())
                    .upperCategoryENUM(tempHandedOut.getUpperCategoryENUM())
                    .handedOutVerifyingStatusENUM(tempHandedOut.getTempHandedOutVerifyingStatusENUM())
                    .build();
            applier.addHandedOut(newHandedOut);
            handedOutRepository.save(newHandedOut);
        }

        //협력업체신청서 내용 저장
        for(TempOCR tempOCR : tempOCRList){
            if(tempOCR.getCategory().equals("신용평가등급")){
                Finance finance = Finance.builder()
                        .category(tempOCR.getCategory())
                        .value(tempOCR.getValue())
                        .build();
                applier.addFinance(finance);
                financeRepository.save(finance);
            }
            else if(tempOCR.getCategory().equals("자본금")){
                Finance finance = Finance.builder()
                        .category(tempOCR.getCategory())
                        .value(tempOCR.getValue())
                        .build();
                applier.addFinance(finance);
                financeRepository.save(finance);
            }
            else if(tempOCR.getCategory().contains("보유면허1") && tempOCR.getValue() != null){
                License license = licenseRepository.findByLicenseSeq("보유면허1");
                if(license == null){
                    License newLicense = License.builder()
                            .licenseSeq("보유면허1")
                            .build();
                    switch (tempOCR.getCategory()) {
                        case "보유면허1(업종)" -> newLicense.setLicenseName(tempOCR.getValue());
                        case "보유면허1(등록번호)" -> newLicense.setLicenseNum(tempOCR.getValue());
                        case "보유면허1(년도)" -> newLicense.setLicenseYear(tempOCR.getValue());
                        case "보유면허1(시평액)" -> newLicense.setCapacityValue(Long.parseLong(tempOCR.getValue()));
                    }
                    licenseRepository.save(newLicense);
                }
                else{
                    switch (tempOCR.getCategory()) {
                        case "보유면허1(업종)" -> license.setLicenseName(tempOCR.getValue());
                        case "보유면허1(등록번호)" -> license.setLicenseNum(tempOCR.getValue());
                        case "보유면허1(년도)" -> license.setLicenseYear(tempOCR.getValue());
                        case "보유면허1(시평액)" -> license.setCapacityValue(Long.parseLong(tempOCR.getValue()));
                    }
                    licenseRepository.save(license);
                }
            }
            else if(tempOCR.getCategory().contains("보유면허2") && tempOCR.getValue() != null){
                License license = licenseRepository.findByLicenseSeq("보유면허2");
                if(license == null){
                    License newLicense = License.builder()
                            .licenseSeq("보유면허2")
                            .build();
                    switch (tempOCR.getCategory()) {
                        case "보유면허2(업종)" -> newLicense.setLicenseName(tempOCR.getValue());
                        case "보유면허2(등록번호)" -> newLicense.setLicenseNum(tempOCR.getValue());
                        case "보유면허2(년도)" -> newLicense.setLicenseYear(tempOCR.getValue());
                        case "보유면허2(시평액)" -> newLicense.setCapacityValue(Long.parseLong(tempOCR.getValue()));
                    }
                    licenseRepository.save(newLicense);
                }
                else{
                    switch (tempOCR.getCategory()) {
                        case "보유면허2(업종)" -> license.setLicenseName(tempOCR.getValue());
                        case "보유면허2(등록번호)" -> license.setLicenseNum(tempOCR.getValue());
                        case "보유면허2(년도)" -> license.setLicenseYear(tempOCR.getValue());
                        case "보유면허2(시평액)" -> license.setCapacityValue(Long.parseLong(tempOCR.getValue()));
                    }
                    licenseRepository.save(license);
                }
            }
            else if(tempOCR.getCategory().contains("보유면허3") && tempOCR.getValue() != null){
                License license = licenseRepository.findByLicenseSeq("보유면허3");
                if(license == null){
                    License newLicense = License.builder()
                            .licenseSeq("보유면허3")
                            .build();
                    switch (tempOCR.getCategory()) {
                        case "보유면허3(업종)" -> newLicense.setLicenseName(tempOCR.getValue());
                        case "보유면허3(등록번호)" -> newLicense.setLicenseNum(tempOCR.getValue());
                        case "보유면허3(년도)" -> newLicense.setLicenseYear(tempOCR.getValue());
                        case "보유면허3(시평액)" -> newLicense.setCapacityValue(Long.parseLong(tempOCR.getValue()));
                    }
                    licenseRepository.save(newLicense);
                }
                else{
                    switch (tempOCR.getCategory()) {
                        case "보유면허3(업종)" -> license.setLicenseName(tempOCR.getValue());
                        case "보유면허3(등록번호)" -> license.setLicenseNum(tempOCR.getValue());
                        case "보유면허3(년도)" -> license.setLicenseYear(tempOCR.getValue());
                        case "보유면허3(시평액)" -> license.setCapacityValue(Long.parseLong(tempOCR.getValue()));
                    }
                    licenseRepository.save(license);
                }
            }
        }

        //tempsaved 내용 저장
        applier.updateApplierFromTempSaved(
                tempSaved.getCorporateApplicationNum(),
                tempSaved.getCompanyPhoneNum(),
                tempSaved.getType(),
                tempSaved.getCompanyAddress(),
                tempSaved.getCompanyIntro()
        );
        application.updateApplicationFromTempSaved(tempSaved.getWorkTypeApplying());

    }



}
