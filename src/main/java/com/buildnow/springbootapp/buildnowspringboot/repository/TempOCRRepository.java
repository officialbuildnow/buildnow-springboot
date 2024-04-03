package com.buildnow.springbootapp.buildnowspringboot.repository;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.Applier;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.Application;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.TempOCR;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TempOCRRepository extends JpaRepository<TempOCR, Long> {
    List<TempOCR> findByApplication(
            Application application
    );

    Boolean existsByApplicationAndCategory(Application application, String category);
}
