package com.buildnow.springbootapp.buildnowspringboot.repository.tempSave;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.Application;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.TempPrerequisite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TempPrerequisiteRepository extends JpaRepository<TempPrerequisite, Long> {
    boolean existsByApplicationAndPrerequisiteName(Application application, String prerequisiteName);
}
