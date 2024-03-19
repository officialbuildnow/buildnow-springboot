package com.buildnow.springbootapp.buildnowspringboot.entitiy.applierInfo;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.Applier;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.recruitment.PaperRequired;
import com.buildnow.springbootapp.buildnowspringboot.repository.HandedOutRepository;
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
