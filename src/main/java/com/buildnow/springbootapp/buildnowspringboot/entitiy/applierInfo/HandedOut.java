package com.buildnow.springbootapp.buildnowspringboot.entitiy.applierInfo;

import com.buildnow.springbootapp.buildnowspringboot.ENUM.HandedOutVerifyingStatusENUM;
import com.buildnow.springbootapp.buildnowspringboot.ENUM.RequiredLevelENUM;
import com.buildnow.springbootapp.buildnowspringboot.ENUM.UpperCategoryENUM;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.Applier;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class HandedOut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String documentName;
    @Column(length = 2048)
    private String documentUrl;
    @Setter
    @Enumerated(EnumType.STRING)
    private RequiredLevelENUM requiredLevelENUM;
    @Setter
    @Enumerated(EnumType.STRING)
    private UpperCategoryENUM upperCategoryENUM;
    //검수전이 디폴트
    @Setter
    @Enumerated(EnumType.STRING)
    private HandedOutVerifyingStatusENUM handedOutVerifyingStatusENUM;
    @Setter
    @ManyToOne
    @JsonBackReference(value="applier-handedOut")
    private Applier applier;

    @Builder
    public HandedOut(
            String documentName,
            String documentUrl,
            RequiredLevelENUM requiredLevelENUM,
            UpperCategoryENUM upperCategoryENUM
            ){
        this.documentName = documentName;
        this.documentUrl = documentUrl;
        this.requiredLevelENUM = requiredLevelENUM;
        this.upperCategoryENUM = upperCategoryENUM;
        this.handedOutVerifyingStatusENUM = HandedOutVerifyingStatusENUM.READY;
    }

}
