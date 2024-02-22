package com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.Recruiter;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.Application;
import jakarta.persistence.*;
import java.util.List;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class Recruitment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate deadline;
    private Long threshold;

    @ManyToOne
    private Recruiter recruiter;

    @OneToMany(mappedBy = "recruitment")
    private List<RecruitingWorkType> recruitingWorkTypeList;

    @OneToMany(mappedBy = "recruitment")
    private List<Prerequisite> prerequisiteList;

    @OneToMany(mappedBy = "recruitment")
    private List<UpperCategoryGrading> upperCategoryGradingList;

    @OneToMany(mappedBy = "recruitment")
    private List<Application> applicationList;

}
