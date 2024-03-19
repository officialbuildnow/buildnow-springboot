package com.buildnow.springbootapp.buildnowspringboot.service;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.Applier;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.applierInfo.Patent;
import com.buildnow.springbootapp.buildnowspringboot.repository.ApplierRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.PatentRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PatentService {
    private final PatentRepository patentRepository;
    private final ApplierRepository applierRepository;
    @Transactional
    public Patent createNewPatent(String patentName, String patentURL, String applierName) throws RuntimeException {
        if(patentName == null) {
            throw new RuntimeException("특허가 첨부되지 않았습니다.");
        }
        Applier applier = applierRepository.findByUsername(applierName);
        if(patentRepository.existsByPatentNameAndApplier(patentName, applier)){
            throw new RuntimeException("이미 등록한 면허입니다.(서류 제출여부는 확인 불가)");
        }
        Patent newPatent = new Patent(
                patentName,
                patentURL
        );
        newPatent.setApplier(applier);
        return patentRepository.save(newPatent);
    }
}
