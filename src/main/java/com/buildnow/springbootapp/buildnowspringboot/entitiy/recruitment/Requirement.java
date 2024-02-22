package com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class Requirement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String documentName;

    @ManyToOne
    private UpperCategoryGrading upperCategoryGrading;
}
