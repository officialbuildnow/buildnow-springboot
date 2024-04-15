package com.buildnow.springbootapp.buildnowspringboot.entitiy.capacityValue;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CompanyByYearAndLicense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;
    private String ceoName;
    private String location;
    private String companyPhoneNum;
    private String licenseNum;
    private Long mechanicNum;
    private Long totalCapacityValue;
    private Long constructionCapacityValue;
    private Long businessCapacityValue;
    private Long creditWorthinessCapacityValue;
    private Long techCapacityValue;
    private Long lastYearPerformance;

    @Setter
    @ManyToOne
    @JsonBackReference(value = "capacityValue-companyByYearAndLicense")
    private CapacityValue capacityValue;

    @Builder
    public CompanyByYearAndLicense(
            String companyName,
            String ceoName,
            String location,
            String companyPhoneNum,
            String licenseNum,
            Long mechanicNum,
            Long totalCapacityValue,
            Long constructionCapacityValue,
            Long businessCapacityValue,
            Long creditWorthinessCapacityValue,
            Long techCapacityValue,
            Long lastYearPerformance
    ){
        this.companyName = companyName;
        this.ceoName = ceoName;
        this.location = location;
        this.companyPhoneNum = companyPhoneNum;
        this.licenseNum = licenseNum;
        this.mechanicNum = mechanicNum;
        this.totalCapacityValue = totalCapacityValue;
        this.constructionCapacityValue = constructionCapacityValue;
        this.businessCapacityValue = businessCapacityValue;
        this.creditWorthinessCapacityValue = creditWorthinessCapacityValue;
        this.techCapacityValue = techCapacityValue;
        this.lastYearPerformance = lastYearPerformance;
    }
}
