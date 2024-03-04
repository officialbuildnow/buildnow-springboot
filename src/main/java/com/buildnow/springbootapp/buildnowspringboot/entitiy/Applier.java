package com.buildnow.springbootapp.buildnowspringboot.entitiy;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.Application;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.applierInfo.Finance;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.applierInfo.History;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.applierInfo.PaperReq;
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
    @Lob
    private String companyIntro;
    private boolean hadAccident;
    private LocalDate estDate;

    @ManyToOne
    private Application application;

    @OneToOne(mappedBy = "applier", cascade = CascadeType.ALL, orphanRemoval = true)
    private Finance finance;

    @OneToMany(mappedBy = "applier", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<History> historyList;

    @OneToMany(mappedBy = "applier", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PaperReq> paperReqList;


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

}
