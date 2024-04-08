package com.buildnow.springbootapp.buildnowspringboot.repository;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.ApplicationEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationEvaluationRepository extends JpaRepository<ApplicationEvaluation, Long> {
}
