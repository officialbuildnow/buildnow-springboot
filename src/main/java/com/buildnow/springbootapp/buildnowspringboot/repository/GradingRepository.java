package com.buildnow.springbootapp.buildnowspringboot.repository;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment.Grading;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradingRepository extends JpaRepository<Grading, Long> {
    List<Grading> findByRecruitment(Recruitment recruitment);

    Grading findByRecruitmentAndCategory(Recruitment recruitment, String category);
}
