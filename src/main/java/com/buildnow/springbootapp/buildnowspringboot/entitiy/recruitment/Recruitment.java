package com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.Recruiter;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.Application;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Recruitment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate deadline;
    private Long threshold;

    @ManyToOne
    @JsonBackReference
    private Recruiter recruiter;

    @OneToMany(mappedBy = "recruitment", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<RecruitingWorkType> recruitingWorkTypeList;

    @OneToMany(mappedBy = "recruitment", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Prerequisite> prerequisiteList;

    @OneToMany(mappedBy = "recruitment", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<UpperCategory> upperCategoryGradingList;

    @OneToMany(mappedBy = "recruitment", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Application> applicationList;

    @OneToMany(mappedBy = "recruitment", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<PaperRequired> paperRequiredList;
    public Recruitment (
            LocalDate deadline,
            Long threshold
    ){
        this.deadline = deadline;
        this.threshold = threshold;
    }

    public void setRecruiter(Recruiter recruiter){
        this.recruiter = recruiter;
        recruiter.getRecruitmentList().add(this);
    }

    public void removeRecruiter(){
        if(this.recruiter != null){
            this.recruiter.getRecruitmentList().remove(this);
        }
        this.recruiter = null;
    }
}
