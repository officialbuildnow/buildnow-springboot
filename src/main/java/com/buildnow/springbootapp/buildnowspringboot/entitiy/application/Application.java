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
    private boolean isNew = false;
    private boolean isRecommended = false;
    @CreatedDate
    private LocalDate appliedDate;
    private String workTypeApplying;
    @Column
    private boolean isRead = false;
    private boolean isChecked = false;
    private boolean isAdminChecked = false; //ADMIN이 검수끝나면 true로 업데이트
    private boolean isSubmit = false; //Applier가 일단 제출(ADMIN 단계로 돌입)하면 true로 바꿈.

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ApplicationEvaluation> applicationEvaluationList;

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<TempOCR> tempOCRList;

    @Setter
    @ManyToOne
    @JsonBackReference
    private Applier applier;

    @Setter
    @ManyToOne
    @JsonBackReference
    private Recruitment recruitment;

    @Setter
    @OneToOne
    @JsonManagedReference
    private TempSaved tempSaved;

    @Setter
    @OneToOne
    @JsonManagedReference
    private TempPrerequisite tempPrerequisite;

    @Builder
    public Application(
        String workTypeApplying
    ){
        this.workTypeApplying = workTypeApplying;
        this.applicationEvaluationList = new ArrayList<>();
        this.tempOCRList = new ArrayList<>();
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


}
