package com.buildnow.springbootapp.buildnowspringboot.service;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.TempOCR;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.TempPrerequisite;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.TempSaved;
import com.buildnow.springbootapp.buildnowspringboot.repository.tempSave.TempOCRRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.tempSave.TempPrerequisiteRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.tempSave.TempSavedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConvertTempToRealService {
    private final TempSavedRepository tempSavedRepository;
    private final TempOCRRepository tempOCRRepository;
    private final TempPrerequisiteRepository tempPrerequisiteRepository;
}
