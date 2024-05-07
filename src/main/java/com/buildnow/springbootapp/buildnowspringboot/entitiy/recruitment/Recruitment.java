package com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.Recruiter;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.Application;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import lombok.*;
import org.checkerframework.checker.units.qual.A;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recruitment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String recruitmentTitle;
    private LocalDate deadline;
    private Long threshold;

    @Setter
    @ManyToOne
    @JsonBackReference(value = "recruiter-recruitment")
    private Recruiter recruiter;

    @OneToMany(mappedBy = "recruitment", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value="recruitment-recruitingWorkType")
    private List<RecruitingWorkType> recruitingWorkTypeList;

    @OneToMany(mappedBy = "recruitment", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value="recruitment-prerequisite")
    private List<Prerequisite> prerequisiteList;

    @OneToMany(mappedBy = "recruitment", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value="recruitment-grading")
    private List<Grading> gradingList;

    @OneToMany(mappedBy = "recruitment", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value="recruitment-application")
    private List<Application> applicationList;


    @Builder
    public Recruitment (
            LocalDate deadline,
            Long threshold,
            String recruitmentTitle
    ){
        this.deadline = deadline;
        this.threshold = threshold;
        this.recruitmentTitle = recruitmentTitle;
        this.recruitingWorkTypeList = new ArrayList<>();
        this.prerequisiteList = new ArrayList<>();
        this.gradingList = new ArrayList<>();
        this.applicationList = new ArrayList<>();
    }

    public void addRecruitingWorkType(RecruitingWorkType recruitingWorkType){
        recruitingWorkTypeList.add(recruitingWorkType);
        recruitingWorkType.setRecruitment(this);
    }

    public void removeRecruitingWorkType(RecruitingWorkType recruitingWorkType){
        recruitingWorkTypeList.remove(recruitingWorkType);
        recruitingWorkType.setRecruitment(null);
    }

    public void addPrerequisite(Prerequisite prerequisite){
        prerequisiteList.add(prerequisite);
        prerequisite.setRecruitment(this);
    }
    public void removePrerequisite(Prerequisite prerequisite){
        prerequisiteList.remove(prerequisite);
        prerequisite.setRecruitment(null);
    }

    public void addGrading(Grading grading){
        gradingList.add(grading);
        grading.setRecruitment(this);
    }
    public void removeGrading(Grading grading){
        gradingList.remove(grading);
        grading.setRecruitment(null);
    }
    public void addApplication(Application application){
        applicationList.add(application);
        application.setRecruitment(this);
    }
    public void removeApplication(Application application){
        applicationList.remove(application);
        application.setRecruitment(null);
    }
}
