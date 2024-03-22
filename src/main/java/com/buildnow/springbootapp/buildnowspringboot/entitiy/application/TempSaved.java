package com.buildnow.springbootapp.buildnowspringboot.entitiy.application;

import com.buildnow.springbootapp.buildnowspringboot.ENUM.BusinessTypeENUM;
import com.buildnow.springbootapp.buildnowspringboot.repository.ApplicationRepository;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
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

    @OneToOne
    @JsonBackReference
    private Application application;

    @OneToMany(mappedBy = "tempSaved", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TempHandedOut> tempHandedOutList = new ArrayList<>();

    public void setApplication(Application application){
        this.application = application;
        application.setTempSaved(this);
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
