package com.buildnow.springbootapp.buildnowspringboot.repository;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.Applier;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.applierInfo.HandedOut;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HandedOutRepository extends JpaRepository<HandedOut, Long> {
    boolean existsByApplierAndDocumentName(Applier applier, String documentName);

    List<HandedOut> findByDocumentNameAndApplier(String documentName, Applier applier);
}
