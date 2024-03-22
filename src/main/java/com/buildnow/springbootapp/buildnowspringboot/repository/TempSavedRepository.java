package com.buildnow.springbootapp.buildnowspringboot.repository;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.Application;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.TempSaved;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TempSavedRepository extends JpaRepository<TempSaved, Long> {
    TempSaved findByApplication(Application application);
}
