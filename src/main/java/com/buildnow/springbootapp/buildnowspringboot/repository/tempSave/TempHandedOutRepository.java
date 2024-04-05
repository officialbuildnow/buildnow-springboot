package com.buildnow.springbootapp.buildnowspringboot.repository.tempSave;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.TempHandedOut;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.TempSaved;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TempHandedOutRepository extends JpaRepository<TempHandedOut, Long> {
    TempHandedOut findByDocumentNameAndTempSaved(String documentName, TempSaved tempSaved);
}
