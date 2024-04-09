package com.buildnow.springbootapp.buildnowspringboot.entitiy;

import com.buildnow.springbootapp.buildnowspringboot.ENUM.BusinessTypeENUM;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.Application;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.License;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.applierInfo.Finance;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.applierInfo.HandedOut;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    private boolean hadAccident;
    private LocalDate estDate;

    @OneToMany(mappedBy = "applier", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value="applier-application")
    private List<Application> applicationList;

    @OneToMany(mappedBy = "applier", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value="applier-finance")
    private List<Finance> financeList;

    @OneToMany(mappedBy = "applier", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value="applier-handedOut")
    private List<HandedOut> handedOutList;

    @OneToMany(mappedBy = "applier", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "applier-license")
    private List<License> licenseList;

    @Builder
    public Applier (
            String businessId, String companyName, String managerName, String managerPhoneNum, String managerEmail, String username, String password
    ){
        this.businessId = businessId;
        this.companyName = companyName;
        this.managerName = managerName;
        this.managerPhoneNum = managerPhoneNum;
        this.managerEmail = managerEmail;
        this.username = username;
        this.password = password;
        this.role="ROLE_APPLIER";
        this.hadAccident = false;
        this.applicationList = new ArrayList<>();
        this.handedOutList = new ArrayList<>();
        this.licenseList = new ArrayList<>();
        this.financeList = new ArrayList<>();
    }

    public void updateApplierFromTempSaved(
            String corporateApplicationNum,
            String companyPhoneNum,
            BusinessTypeENUM businessTypeENUM,
            String companyAddress,
            String companyIntro
    ){
        this.corporateApplicationNum = corporateApplicationNum;
        this.companyPhoneNum = companyPhoneNum;
        this.type = businessTypeENUM;
        this.companyAddress = companyAddress;
        this.companyIntro = companyIntro;
    }

    public void addFinance(Finance finance){
        financeList.add(finance);
        finance.setApplier(this);
    }

    public void removeFinance(Finance finance){
        financeList.remove(finance);
        finance.setApplier(null);
    }

    public void addLicense(License license){
        licenseList.add(license);
        license.setApplier(this);
    }

    public void removeLicense(License license){
        licenseList.remove(license);
        license.setApplier(null);
    }

    public void addApplication(Application application){
        applicationList.add(application);
        application.setApplier(this);
    }

    public void removeApplication(Application application){
        applicationList.remove(application);
        application.setApplier(null);
    }


    public void addHandedOut(HandedOut handedOut){
        handedOutList.add(handedOut);
        handedOut.setApplier(this);
    }
    public void removeHandedOut(HandedOut handedOut){
        handedOutList.remove(handedOut);
        handedOut.setApplier(null);
    }

}
