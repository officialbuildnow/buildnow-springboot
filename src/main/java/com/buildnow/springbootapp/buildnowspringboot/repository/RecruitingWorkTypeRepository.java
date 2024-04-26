package com.buildnow.springbootapp.buildnowspringboot.repository;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment.RecruitingWorkType;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecruitingWorkTypeRepository extends JpaRepository<RecruitingWorkType, Long> {
    List<RecruitingWorkType> findByRecruitment(Recruitment recruitment);
    boolean existsByRecruitmentAndWorkType(Recruitment recruitment, String workType);
}
