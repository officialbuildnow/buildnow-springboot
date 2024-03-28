package com.buildnow.springbootapp.buildnowspringboot.entitiy.application;

import com.buildnow.springbootapp.buildnowspringboot.ENUM.BusinessTypeENUM;
import com.buildnow.springbootapp.buildnowspringboot.repository.ApplicationRepository;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class TempSaved {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String corporateApplicationNum;
    private String companyPhoneNum;
    private String workTypeApplying;
    private BusinessTypeENUM type;
    private String companyAddress;
    @Lob
    private String companyIntro;

    @Setter
    @OneToOne
    @JsonBackReference
    private Application application;

    @OneToMany(mappedBy = "tempSaved", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TempHandedOut> tempHandedOutList;

    @Builder
    public TempSaved(String corporateApplicationNum,
                     String companyPhoneNum,
                     String workTypeApplying,
                     BusinessTypeENUM type,
                     String companyAddress,
                     String companyIntro){
        this.corporateApplicationNum = corporateApplicationNum;
        this.companyPhoneNum = companyPhoneNum;
        this.workTypeApplying = workTypeApplying;
        this.type = type;
        this.companyAddress = companyAddress;
        this.companyIntro = companyIntro;
        this.tempHandedOutList = new ArrayList<>();
    }

    public void addTempHandedOut(TempHandedOut tempHandedOut){
        tempHandedOutList.add(tempHandedOut);
        tempHandedOut.setTempSaved(this);
    }

    public void removeTempHandedOut(TempHandedOut tempHandedOut){
        tempHandedOutList.remove(tempHandedOut);
        tempHandedOut.setTempSaved(this);
    }

    public void updateTempSaved(String corporateApplicationNum,
                           String companyPhoneNum,
                           String workTypeApplying,
                           BusinessTypeENUM type,
                           String companyAddress,
                           String companyIntro,
                                List<TempHandedOut> tempHandedOutList){
        log.info(tempHandedOutList.get(0).getDocumentName());
        if(!this.corporateApplicationNum.equals(corporateApplicationNum)) {
            this.corporateApplicationNum = corporateApplicationNum;
        }
        if(!this.companyPhoneNum.equals(companyPhoneNum)){
            this.companyPhoneNum = companyPhoneNum;
        }
        if(!this.workTypeApplying.equals(workTypeApplying)){
            this.workTypeApplying = workTypeApplying;
        }
        if(!this.type.equals(type)) {
            this.type = type;
        }
        if(!this.companyAddress.equals(companyAddress)) {
            this.companyAddress = companyAddress;
        }
        if(!this.companyIntro.equals(companyIntro)) {
            this.companyIntro = companyIntro;
        }

        this.tempHandedOutList.clear();
        for(TempHandedOut newHandedOut : tempHandedOutList){
            log.info(String.valueOf(newHandedOut.getDocumentName()));
            this.tempHandedOutList.add(newHandedOut);
            newHandedOut.setTempSaved(this);
        }
    }


}
