package com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment;

import com.buildnow.springbootapp.buildnowspringboot.ENUM.UpperCategoryENUM;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.ApplicationEvaluation;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

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
    @JsonBackReference
    private Recruitment recruitment;

    @OneToMany(mappedBy = "grading", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ApplicationEvaluation> applicationEvaluationList;

    @Builder
    public Grading(String category, Long perfectScore, UpperCategoryENUM upperCategoryENUM){
        this.category = category;
        this.perfectScore = perfectScore;
        this.upperCategoryENUM = upperCategoryENUM;
    }
}
