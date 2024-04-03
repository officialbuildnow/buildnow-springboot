package com.buildnow.springbootapp.buildnowspringboot.repository;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.TempHandedOut;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TempHandedOutRepository extends JpaRepository<TempHandedOut, Long> {
    TempHandedOut findByDocumentName(String documentName);
}
