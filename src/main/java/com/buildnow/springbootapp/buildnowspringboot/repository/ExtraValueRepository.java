package com.buildnow.springbootapp.buildnowspringboot.repository;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.Applier;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.applierInfo.ExtraValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExtraValueRepository extends JpaRepository<ExtraValue, Long> {
    boolean existsByCategoryAndApplier(String category, Applier applier);

    ExtraValue findByCategoryAndApplier(String category, Applier applier);
}
