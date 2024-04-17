package com.buildnow.springbootapp.buildnowspringboot.entitiy.applierInfo;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.Applier;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class License {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String licenseName;
    private String licenseNum;
    private Long capacityValue;
    private String licenseSeq;
    private String licenseYear;
    private Long cvRank;
    private Long percentage;

    @ManyToOne
    @JsonBackReference(value="applier-license")
    Applier applier;

    @Builder
    public License(
            String licenseName,
            String licenseNum,
            Long capacityValue,
            String licenseSeq,
            String licenseYear,
            Long cvRank,
            Long percentage
    ){
        this.licenseName = licenseName;
        this.licenseNum = licenseNum;
        this.capacityValue = capacityValue;
        this.licenseSeq = licenseSeq;
        this.licenseYear = licenseYear;
        this.cvRank = cvRank;
        this.percentage = percentage;
    }

}
