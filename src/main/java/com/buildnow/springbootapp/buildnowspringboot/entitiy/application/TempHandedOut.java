package com.buildnow.springbootapp.buildnowspringboot.entitiy.application;

import com.buildnow.springbootapp.buildnowspringboot.ENUM.RequiredLevelENUM;
import com.buildnow.springbootapp.buildnowspringboot.ENUM.UpperCategoryENUM;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class TempHandedOut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String documentName;
    private String documentUrl;
    @Setter
    @Enumerated(EnumType.STRING)
    private RequiredLevelENUM requiredLevelENUM;
    @Setter
    @Enumerated(EnumType.STRING)
    private UpperCategoryENUM upperCategoryENUM;
    @Setter
    @ManyToOne
    @JsonBackReference
    private TempSaved tempSaved;


    @Builder
    public TempHandedOut(
            String documentName,
            String documentUrl
    ){
        this.documentName = documentName;
        this.documentUrl = documentUrl;
    }

}
