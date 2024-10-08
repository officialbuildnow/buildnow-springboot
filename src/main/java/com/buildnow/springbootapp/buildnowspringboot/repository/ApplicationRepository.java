package com.buildnow.springbootapp.buildnowspringboot.repository;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.Applier;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.Recruiter;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.Application;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment.Recruitment;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    boolean existsByApplierAndRecruitment(Applier applier, Recruitment recruitment);
    Application findByApplierAndRecruitment(Applier applier, Recruitment recruitment);

    List<Application> findByApplier(Applier applier);
    List<Application> findByRecruitment(Recruitment recruitment);
    List<Application> findByRecruitmentAndIsAdminChecked(Recruitment recruitment, boolean isAdminChecked);
    @NotNull
    Optional<Application> findById(Long id);

    @NotNull
    @EntityGraph(attributePaths = {"applier"})
    List<Application> findAll();
}
