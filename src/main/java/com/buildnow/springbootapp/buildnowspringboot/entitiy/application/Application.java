package com.buildnow.springbootapp.buildnowspringboot.entitiy.application;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.Applier;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment.Recruitment;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
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
    private boolean isSubmit = false;

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ApplicationEvaluation> applicationEvaluationList;

    @ManyToOne
    @JsonBackReference
    private Applier applier;

    @ManyToOne
    @JsonBackReference
    private Recruitment recruitment;

    @OneToOne
    @JsonManagedReference
    private TempSaved tempSaved;
    public Application(
        String workTypeApplying
    ){
        this.workTypeApplying = workTypeApplying;
    }

    public void setRecruitment(Recruitment recruitment){
        this.recruitment = recruitment;
        recruitment.getApplicationList().add(this);
    }

    public void removeRecruitment(){
        if(this.recruitment != null){
            this.recruitment.getApplicationList().remove(this);
        }
        this.recruitment = null;
    }

    public void setApplier(Applier applier){
        this.applier = applier;
        applier.getApplicationList().add(this);
    }

    public void removeApplier(Applier applier){
        if(this.applier != null){
            applier.getApplicationList().remove(this);
        }
        this.applier = null;
    }

    public void setTempSaved(TempSaved tempSaved){
        this.tempSaved = tempSaved;
    }
}
