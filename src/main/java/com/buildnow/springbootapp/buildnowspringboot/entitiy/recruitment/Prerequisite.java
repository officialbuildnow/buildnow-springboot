package com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Prerequisite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String prerequisiteName;
    private Long prerequisiteValue;

    @Setter
    @ManyToOne
    @JsonBackReference(value="recruitment-prerequisite")
    private Recruitment recruitment;

    @Builder
    public Prerequisite(String prerequisiteName, Long prerequisiteValue){
        this.prerequisiteName = prerequisiteName;
        this.prerequisiteValue = prerequisiteValue;
    }


}
