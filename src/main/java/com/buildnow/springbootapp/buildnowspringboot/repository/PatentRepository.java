package com.buildnow.springbootapp.buildnowspringboot.repository;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.Applier;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.applierInfo.Patent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatentRepository extends JpaRepository<Patent, Long> {
    boolean existsByPatentNameAndApplier(String patentName, Applier applier);
}
