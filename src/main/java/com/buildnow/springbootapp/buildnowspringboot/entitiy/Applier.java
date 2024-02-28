package com.buildnow.springbootapp.buildnowspringboot.entitiy;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.Application;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.applierInfo.Finance;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.applierInfo.History;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.applierInfo.PaperReq;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.applierInfo.PossibleWorkType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class Applier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String businessId;
    private String role="APPLIER";
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

    @OneToOne(mappedBy = "applier", cascade = CascadeType.ALL)
    private Finance finance;

    @OneToMany(mappedBy = "applier")
    private List<History> historyList;

    @OneToMany(mappedBy = "applier")
    private List<PaperReq> paperReqList;

    @OneToMany(mappedBy = "applier")
    private List<PossibleWorkType> possibleWorkTypeList;
}
