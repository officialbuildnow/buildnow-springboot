package com.buildnow.springbootapp.buildnowspringboot.entitiy.application;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment.Grading;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class ApplicationEvaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long score;

    @Setter
    @ManyToOne
    @JsonBackReference(value="grading-applicationEvaluation")
    private Grading grading;

    @Setter
    @ManyToOne
    @JsonBackReference(value="application-applicationEvaluation")
    private Application application;

    @Builder
    public ApplicationEvaluation (Long score){
        this.score = score;
    }
}
