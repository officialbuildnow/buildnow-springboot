package com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class UpperCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String upperCategory;

    @ManyToOne
    @JsonBackReference
    private Recruitment recruitment;


    @OneToMany(mappedBy = "upperCategoryGrading", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Grading> gradingList;
}
