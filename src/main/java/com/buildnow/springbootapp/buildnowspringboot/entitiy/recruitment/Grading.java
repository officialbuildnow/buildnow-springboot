package com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment;

import com.buildnow.springbootapp.buildnowspringboot.ENUM.UpperCategoryENUM;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.ApplicationEvaluation;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Grading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String category;
    private Long perfectScore;
    @Enumerated(EnumType.STRING)
    private UpperCategoryENUM upperCategoryENUM;

    @Setter
    @ManyToOne
    @JsonBackReference(value="recruitment-grading")
    private Recruitment recruitment;

    @OneToMany(mappedBy = "grading", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value="grading-applicationEvaluation")
    private List<ApplicationEvaluation> applicationEvaluationList;

    @Builder
    public Grading(String category, Long perfectScore, UpperCategoryENUM upperCategoryENUM){
        this.category = category;
        this.perfectScore = perfectScore;
        this.upperCategoryENUM = upperCategoryENUM;
        this.applicationEvaluationList = new ArrayList<>();
    }

    public void addApplicationEvaluation(ApplicationEvaluation applicationEvaluation){
        this.applicationEvaluationList.add(applicationEvaluation);
        applicationEvaluation.setGrading(this);
    }

    public void removeApplicationEvaluation(ApplicationEvaluation applicationEvaluation){
        this.applicationEvaluationList.remove(applicationEvaluation);
        applicationEvaluation.setGrading(null);
    }
}
