package com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment.Recruitment;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RecruitingWorkType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String workType;

    @ManyToOne
    @JsonBackReference
    private Recruitment recruitment;
}
