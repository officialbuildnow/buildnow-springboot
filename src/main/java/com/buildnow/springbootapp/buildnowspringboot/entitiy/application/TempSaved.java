package com.buildnow.springbootapp.buildnowspringboot.entitiy.application;

import com.buildnow.springbootapp.buildnowspringboot.ENUM.BusinessTypeENUM;
import com.buildnow.springbootapp.buildnowspringboot.repository.ApplicationRepository;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private String licenseName; //신한건설용 API
    @Lob
    private String companyIntro;

    @Setter
    @OneToOne
    @JsonBackReference(value="TempSaved-Application")
    private Application application;

    @OneToMany(mappedBy = "tempSaved", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value="tempSaved-tempHandedOut")
    private List<TempHandedOut> tempHandedOutList;

    @Builder
    public TempSaved(String corporateApplicationNum,
                     String companyPhoneNum,
                     String workTypeApplying,
                     BusinessTypeENUM type,
                     String companyAddress,
                     String companyIntro,
                     String licenseName){
        this.corporateApplicationNum = corporateApplicationNum;
        this.companyPhoneNum = companyPhoneNum;
        this.workTypeApplying = workTypeApplying;
        this.type = type;
        this.companyAddress = companyAddress;
        this.companyIntro = companyIntro;
        this.licenseName = licenseName; //신한건설 API
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
                                String licenseName,
                                List<TempHandedOut> tempHandedOutList) {

        if(corporateApplicationNum != null) {
            this.corporateApplicationNum = corporateApplicationNum;
        }
        if(companyPhoneNum != null) {
            this.companyPhoneNum = companyPhoneNum;
        }
        if(workTypeApplying != null) {
            this.workTypeApplying = workTypeApplying;
        }
        if(type != null) {
            this.type = type;
        }
        if(companyAddress != null) {
            this.companyAddress = companyAddress;
        }
        if(companyIntro != null) {
            this.companyIntro = companyIntro;
        }

        if(licenseName != null){
            this.licenseName = licenseName;
        }

        if(tempHandedOutList != null && !tempHandedOutList.isEmpty()) {
            this.tempHandedOutList.clear();
            this.tempHandedOutList.addAll(tempHandedOutList);
            tempHandedOutList.forEach(handOut -> handOut.setTempSaved(this));
        }
    }



}
