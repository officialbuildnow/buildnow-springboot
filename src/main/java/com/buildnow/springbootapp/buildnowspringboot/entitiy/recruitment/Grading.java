package com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.ApplicationEvaluation;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class Grading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String category;
    private Long perfectScore;

    @ManyToOne
    private UpperCategory upperCategoryGrading;

    @OneToMany(mappedBy = "grading", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ApplicationEvaluation> applicationEvaluationList;

}
