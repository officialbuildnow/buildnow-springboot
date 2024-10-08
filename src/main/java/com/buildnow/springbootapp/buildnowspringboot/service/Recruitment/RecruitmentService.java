package com.buildnow.springbootapp.buildnowspringboot.service.Recruitment;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.Recruiter;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment.Recruitment;
import com.buildnow.springbootapp.buildnowspringboot.repository.GradingRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.RecruiterRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class RecruitmentService {
    private final RecruitmentRepository recruitmentRepository;
    private final RecruiterRepository recruiterRepository;
    @Transactional
    public Recruitment createRecruitment(LocalDate deadLine, Long threshold, String recruiterName){
        Recruiter recruiter = recruiterRepository.findByUsername(recruiterName);
        Recruitment newRecruitment = Recruitment.builder()
                .deadline(deadLine)
                .threshold(threshold)
                .build();
        recruiter.addRecruitment(newRecruitment);
        return recruitmentRepository.save(newRecruitment);
    }

}
