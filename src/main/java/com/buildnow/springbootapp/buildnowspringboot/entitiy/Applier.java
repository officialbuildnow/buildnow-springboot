package com.buildnow.springbootapp.buildnowspringboot.entitiy;

import com.buildnow.springbootapp.buildnowspringboot.ENUM.BusinessTypeENUM;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.Application;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.applierInfo.Finance;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.applierInfo.History;
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

    @OneToMany(mappedBy = "applier", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference(value="applier-application")
    private List<Application> applicationList;

    @OneToOne(mappedBy = "applier", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value="applier-finance")
    private Finance finance;

    @OneToMany(mappedBy = "applier", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value="applier-history")
    private List<History> historyList;

    @OneToMany(mappedBy = "applier", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value="applier-handedOut")
    private List<HandedOut> handedOutList;

    @Builder
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
        this.hadAccident = false;
        this.applicationList = new ArrayList<>();
        this.historyList = new ArrayList<>();
        this.handedOutList = new ArrayList<>();
    }

    public void setFinance(Finance finance){
       if(finance == null){
           if(this.finance != null){
               this.finance.setApplier(null);
           }
           this.finance = null;
       }
       else{
           finance.setApplier(this);
           this.finance = finance;
       }
    }

    public void addApplication(Application application){
        applicationList.add(application);
        application.setApplier(this);
    }

    public void removeApplication(Application application){
        applicationList.remove(application);
        application.setApplier(null);
    }

    public void addHistory(History history){
        historyList.add(history);
        history.setApplier(this);
    }

    public void removeHistory(History history){
        historyList.remove(history);
        history.setApplier(null);
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
