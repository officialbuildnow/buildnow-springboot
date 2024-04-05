package com.buildnow.springbootapp.buildnowspringboot.entitiy.application;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.Applier;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment.Recruitment;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.checkerframework.checker.units.qual.A;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean isNew;
    private boolean isRecommended;
    @CreatedDate
    private LocalDate appliedDate;
    private String workTypeApplying;
    @Column
    private boolean isRead;
    private boolean isChecked;
    private boolean isAdminChecked; //ADMIN이 검수끝나면 true로 업데이트
    private boolean isSubmit; //Applier가 일단 제출(ADMIN 단계로 돌입)하면 true로 바꿈.

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value="application-applicationEvaluation")
    private List<ApplicationEvaluation> applicationEvaluationList;

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value="application-tempOCR")
    private List<TempOCR> tempOCRList;

    @Setter
    @ManyToOne
    @JsonBackReference(value="applier-application")
    private Applier applier;

    @Setter
    @ManyToOne
    @JsonBackReference(value="recruitment-application")
    private Recruitment recruitment;

    @Setter
    @OneToOne(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value="TempSaved-Application")
    private TempSaved tempSaved;

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value="application-tempPrerequisite")
    private List<TempPrerequisite> tempPrerequisiteList;

    @Builder
    public Application(
            Applier applier,
            Recruitment recruitment
    ){
        this.applier = applier;
        this.recruitment = recruitment;
        this.isNew = false;
        this.isRecommended = false;
        this.isRead = false;
        this.isChecked = false;
        this.isAdminChecked = false;
        this.isSubmit = false;
        this.applicationEvaluationList = new ArrayList<>();
        this.tempOCRList = new ArrayList<>();
        this.tempPrerequisiteList = new ArrayList<>();
    }

    public void addTempPrerequisite(TempPrerequisite tempPrerequisite){
        tempPrerequisiteList.add(tempPrerequisite);
        tempPrerequisite.setApplication(this);
    }

    public void removeTempPrerequisite(TempPrerequisite tempPrerequisite){
        tempPrerequisiteList.remove(tempPrerequisite);
        tempPrerequisite.setApplication(null);
    }

    public void addApplicationEvaluation(ApplicationEvaluation applicationEvaluation){
        applicationEvaluationList.add(applicationEvaluation);
        applicationEvaluation.setApplication(this);
    }

    public void removeApplicationEvaluation(ApplicationEvaluation applicationEvaluation){
        applicationEvaluationList.remove(applicationEvaluation);
        applicationEvaluation.setApplication(null);
    }

    public void addTempOCR(TempOCR tempOCR){
        tempOCRList.add(tempOCR);
        tempOCR.setApplication(this);
    }

    public void removeTempOCR(TempOCR tempOCR){
        tempOCRList.remove(tempOCR);
        tempOCR.setApplication(null);
    }

    public void updateSubmitTrue(){
        this.isSubmit = true;
    }

    public void updateIsAdminTrue(){
        this.isAdminChecked = true;
    }

    public void updateIsAdminFalse(){
        this.isAdminChecked = false;
    }
}
