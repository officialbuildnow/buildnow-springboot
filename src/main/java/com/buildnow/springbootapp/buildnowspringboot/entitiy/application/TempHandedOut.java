package com.buildnow.springbootapp.buildnowspringboot.entitiy.application;

import com.buildnow.springbootapp.buildnowspringboot.ENUM.HandedOutVerifyingStatusENUM;
import com.buildnow.springbootapp.buildnowspringboot.ENUM.RequiredLevelENUM;
import com.buildnow.springbootapp.buildnowspringboot.ENUM.UpperCategoryENUM;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class TempHandedOut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String documentName;
    @Column(length = 2048)
    private String documentUrl;

    @Enumerated(EnumType.STRING)
    private RequiredLevelENUM requiredLevelENUM;

    @Enumerated(EnumType.STRING)
    private UpperCategoryENUM upperCategoryENUM;

    //검수전이 디폴트

    @Enumerated(EnumType.STRING)
    private HandedOutVerifyingStatusENUM tempHandedOutVerifyingStatusENUM;

    @ManyToOne
    @JsonBackReference(value="tempSaved-tempHandedOut")
    private TempSaved tempSaved;


    @Builder
    public TempHandedOut(
            String documentName,
            String documentUrl
    ){
        this.documentName = documentName;
        this.documentUrl = documentUrl;
    }

    public void updateVerificationStatus(HandedOutVerifyingStatusENUM statusENUM){
        this.tempHandedOutVerifyingStatusENUM = statusENUM;
    }

}
