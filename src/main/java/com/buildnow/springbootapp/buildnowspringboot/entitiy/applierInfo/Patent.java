package com.buildnow.springbootapp.buildnowspringboot.entitiy.applierInfo;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.Applier;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Patent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String patentName;
    private String documentURL;

    @ManyToOne
    @JsonBackReference
    private Applier applier;

    public Patent (
            String patentName,
            String documentURL
    ){
        this.patentName = patentName;
        this.documentURL = documentURL;
    }

    public void setApplier(Applier applier){
        this.applier = applier;
        applier.getPatentList().add(this);
    }

    public void removeApplier(Applier applier){
        if(this.applier != null) {
            this.applier.getPatentList().remove(this);
        }
        this.applier = null;
    }
}
