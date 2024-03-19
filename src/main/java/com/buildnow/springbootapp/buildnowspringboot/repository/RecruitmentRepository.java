package com.buildnow.springbootapp.buildnowspringboot.repository;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment.Recruitment;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {

    Optional<Recruitment> findById(Long id);
}
