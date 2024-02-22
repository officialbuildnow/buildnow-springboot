package com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class UpperCategoryGrading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String upperCategory;

    @ManyToOne
    private Recruitment recruitment;

    @OneToMany(mappedBy = "upperCategoryGrading")
    private List<Requirement> requirementList;

    @OneToMany(mappedBy = "upperCategoryGrading")
    private List<Grading> gradingList;
}
