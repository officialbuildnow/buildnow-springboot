package com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class RequiringPatent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String requiringPatent;

    @Setter
    @ManyToOne
    @JsonBackReference(value="recruitingWorkType-requiringPatent")
    private RecruitingWorkType recruitingWorkType;

    public RequiringPatent(String requiringPatent){
        this.requiringPatent = requiringPatent;
    }
}
