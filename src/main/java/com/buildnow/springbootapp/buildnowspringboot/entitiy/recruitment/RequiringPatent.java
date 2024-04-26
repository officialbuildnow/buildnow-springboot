package com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequiringPatent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String patentName;

    @Setter
    @ManyToOne
    @JsonBackReference(value="recruitingWorkType-requiringPatent")
    private RecruitingWorkType recruitingWorkType;

    @Builder
    public RequiringPatent(
            String patentName
    ){
        this.patentName = patentName;
    }
}
