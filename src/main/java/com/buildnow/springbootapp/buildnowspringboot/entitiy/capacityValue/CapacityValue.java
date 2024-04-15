package com.buildnow.springbootapp.buildnowspringboot.entitiy.capacityValue;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CapacityValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int year;
    private String licenseName;

    @OneToMany(mappedBy = "capacityValue" , cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value="capacityValue-companyByYearAndLicense")
    private List<CompanyByYearAndLicense> companyByYearAndLicenseList;

    @Builder
    public CapacityValue (
            int year,
            String licenseName
    ){
        this.year = year;
        this.licenseName = licenseName;
        this.companyByYearAndLicenseList = new ArrayList<>();
    }

    public void addCompanyByYearAndLicense(CompanyByYearAndLicense companyByYearAndLicense){
        companyByYearAndLicenseList.add(companyByYearAndLicense);
        companyByYearAndLicense.setCapacityValue(this);
    }

    public void removeCompanyByYearAndLicense(CompanyByYearAndLicense companyByYearAndLicense){
        companyByYearAndLicenseList.remove(companyByYearAndLicense);
        companyByYearAndLicense.setCapacityValue(null);
    }

}
