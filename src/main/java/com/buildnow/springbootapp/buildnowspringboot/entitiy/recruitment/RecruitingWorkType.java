package com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment.Recruitment;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecruitingWorkType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String workType;

    @Setter
    @ManyToOne
    @JsonBackReference(value="recruitment-recruitingWorkType")
    private Recruitment recruitment;

    @OneToMany(mappedBy = "recruitingWorkType", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value="recruitingWorkType-requiringPatent")
    private List<RequiringPatent> requiringPatentList;
    @Builder
    public RecruitingWorkType(String workType){
        this.workType = workType;
        this.requiringPatentList = new ArrayList<>();
    }

    public void addRequiringPatent(RequiringPatent requiringPatent){
        requiringPatentList.add(requiringPatent);
        requiringPatent.setRecruitingWorkType(this);
    }

    public void removeRequiringPatent(RequiringPatent requiringPatent){
        requiringPatentList.remove(requiringPatent);
        requiringPatent.setRecruitingWorkType(null);
    }
}
