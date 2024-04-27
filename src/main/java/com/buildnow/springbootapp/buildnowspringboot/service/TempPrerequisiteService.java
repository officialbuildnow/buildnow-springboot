package com.buildnow.springbootapp.buildnowspringboot.service;

import com.buildnow.springbootapp.buildnowspringboot.dto.tempSave.TempPrerequisiteDTO;
import com.buildnow.springbootapp.buildnowspringboot.dto.tempSave.TempPrerequisiteListDTO;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.Application;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.TempPrerequisite;
import com.buildnow.springbootapp.buildnowspringboot.repository.ApplicationRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.tempSave.TempPrerequisiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TempPrerequisiteService {
    private final TempPrerequisiteRepository tempPrerequisiteRepository;
    private final ApplicationRepository applicationRepository;
    @Transactional
    public List<TempPrerequisite> createTempPrerequisite(TempPrerequisiteListDTO tempPrerequisiteListDTO,
                                                   Long applicationId){
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(()-> new RuntimeException("해당하는 application이 존재하지 않습니다."));

        for(TempPrerequisiteDTO tempPrerequisiteDTO : tempPrerequisiteListDTO.getTempPrerequisiteDTOList()){
            if(tempPrerequisiteRepository.existsByApplicationAndPrerequisiteName(application, tempPrerequisiteDTO.getPrerequisiteName())){
                throw new RuntimeException("동일한 prerequisiteName 존재, 중복 제거해야함.");
            }
            TempPrerequisite newTempPrerequisite = TempPrerequisite.builder()
                    .prerequisiteName(tempPrerequisiteDTO.getPrerequisiteName())
                    .isPrerequisite(tempPrerequisiteDTO.getIsPrerequisite())
                    .whyMidal(tempPrerequisiteDTO.getWhyMidal())
                    .build();
            application.addTempPrerequisite(newTempPrerequisite);
            tempPrerequisiteRepository.save(newTempPrerequisite);
        }

        return application.getTempPrerequisiteList();
    }

    @Transactional
    public List<TempPrerequisite> retrieveTempPrerequisiteOfApplication(
            Long applicationId
    ){
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("해당하는 application이 존재하지 않습니다."));

        return application.getTempPrerequisiteList();
    }
}
