package com.buildnow.springbootapp.buildnowspringboot.entitiy.application;

import com.buildnow.springbootapp.buildnowspringboot.ENUM.RequiredLevelENUM;
import com.buildnow.springbootapp.buildnowspringboot.ENUM.UpperCategoryENUM;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class TempHandedOut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String documentName;
    private String documentUrl;
    @Enumerated(EnumType.STRING)
    private RequiredLevelENUM requiredLevelENUM;
    @Enumerated(EnumType.STRING)
    private UpperCategoryENUM upperCategoryENUM;
    @ManyToOne
    @JsonBackReference
    private TempSaved tempSaved;


    public TempHandedOut(
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


    public void setTempSaved(TempSaved tempSaved){
        this.tempSaved = tempSaved;
    }

    public void removeTempSaved(){
        if(this.tempSaved != null){
            this.tempSaved.getTempHandedOutList().remove(this);
        }
        this.tempSaved = null;
    }
}
