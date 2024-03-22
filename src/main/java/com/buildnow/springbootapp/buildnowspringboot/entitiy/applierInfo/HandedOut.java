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
    private String documentUrl;
    @Enumerated(EnumType.STRING)
    private RequiredLevelENUM requiredLevelENUM;
    @Enumerated(EnumType.STRING)
    private UpperCategoryENUM upperCategoryENUM;
    //검수전이 디폴트
    @Enumerated(EnumType.STRING)
    private HandedOutVerifyingStatusENUM handedOutVerifyingStatusENUM = HandedOutVerifyingStatusENUM.READY;
    @ManyToOne
    @JsonBackReference
    private Applier applier;

    public HandedOut(
            String documentName,
            String documentUrl
            ){
        this.documentName = documentName;
        this.documentUrl = documentUrl;
    }

    public void setHandedOutUpperCategory(UpperCategoryENUM upperCategoryENUM){
        this.upperCategoryENUM = upperCategoryENUM;
    }

    public void setHandedOutRequiredLevel(RequiredLevelENUM requiredLevelENUM){
        this.requiredLevelENUM = requiredLevelENUM;
    }

    public void setVerificationStatus(HandedOutVerifyingStatusENUM handedOutVerifyingStatusENUM){
        this.handedOutVerifyingStatusENUM = handedOutVerifyingStatusENUM;
    }

    public void setApplier(Applier applier){
        this.applier = applier;
        applier.getHandedOutList().add(this);
    }

    public void removeApplier(){
        if(this.applier != null){
            this.applier.getHandedOutList().remove(this);
        }
        this.applier = null;
    }
}
