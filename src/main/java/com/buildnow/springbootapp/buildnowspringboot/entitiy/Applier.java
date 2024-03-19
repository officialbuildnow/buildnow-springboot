package com.buildnow.springbootapp.buildnowspringboot.entitiy;

import com.buildnow.springbootapp.buildnowspringboot.ENUM.BusinessTypeENUM;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.Application;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.applierInfo.Finance;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.applierInfo.History;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.applierInfo.HandedOut;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.applierInfo.Patent;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Applier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String businessId;
    private String role;
    private String companyName;
    private String ceoName;
    private String companyAddress;
    private String managerName;
    private String managerPhoneNum;
    private String managerEmail;
    private String corporateApplicationNum;
    private String companyPhoneNum;
    private String esg;
    private BusinessTypeENUM type;
    @Lob
    private String companyIntro;
    private boolean hadAccident = false;
    private LocalDate estDate;

    @OneToMany(mappedBy = "applier", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Application> applicationList;

    @OneToOne(mappedBy = "applier", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Finance finance;

    @OneToMany(mappedBy = "applier", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<History> historyList;

    @OneToMany(mappedBy = "applier", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<HandedOut> HandedOutList;

    @OneToMany(mappedBy = "applier", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Patent> patentList;

    public Applier (
            String businessId, String managerName, String managerPhoneNum, String managerEmail, String username, String password
    ){
        this.businessId = businessId;
        this.managerName = managerName;
        this.managerPhoneNum = managerPhoneNum;
        this.managerEmail = managerEmail;
        this.username = username;
        this.password = password;
        this.role="ROLE_APPLIER";
    }

    public void updateApplierInfo(
            String password,
            String companyName,
            String ceoName,
            String companyAddress,
            String managerName,
            String managerPhoneNum,
            String managerEmail,
            String corporateApplicationNum,
            String companyPhoneNum,
            String esg,
            BusinessTypeENUM type,
            String companyIntro,
            boolean hadAccident,
            LocalDate estDate
    ){
        if(password != null) this.password = password;
        if(companyName != null) this.companyName = companyName;
        if(ceoName != null) this.ceoName = ceoName;
        if(companyAddress != null) this.companyAddress = companyAddress;
        if(managerName != null) this.managerName = managerName;
        if(managerPhoneNum != null) this.managerPhoneNum = managerPhoneNum;
        if(managerEmail != null) this.managerEmail = managerEmail;
        if(corporateApplicationNum != null) this.corporateApplicationNum = corporateApplicationNum;
        if(companyPhoneNum != null) this.companyPhoneNum = companyPhoneNum;
        if(esg != null) this.esg = esg;
        if(type != null) this.type = type;
        if(companyIntro != null) this.companyIntro = companyIntro;
        if(hadAccident != this.hadAccident) this.hadAccident = hadAccident;
        if(estDate != null) this.estDate = estDate;
    }

}
