package com.buildnow.springbootapp.buildnowspringboot.entitiy.applierInfo;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.Applier;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Finance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String creditGrade;
    private String cashFlowGrade;
    private String watchGrade;
    private Long salesRevenue;
    private Long operatingMarginRatio;
    private Long netProfitMarginRatio;
    private Long currentRatio;
    private Long quickRatio;
    private Long debtToEquityRatio;
    private Long debtDependency;

    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private Applier applier;

    @Builder
    public Finance(
            String creditGrade,
            String cashFlowGrade,
            String watchGrade,
            Long salesRevenue,
            Long operatingMarginRatio,
            Long netProfitMarginRatio,
            Long currentRatio,
            Long quickRatio,
            Long debtToEquityRatio,
            Long debtDependency
    ){
        this.creditGrade = creditGrade;
        this.cashFlowGrade = cashFlowGrade;
        this.watchGrade = watchGrade;
        this.salesRevenue = salesRevenue;
        this.operatingMarginRatio = operatingMarginRatio;
        this.netProfitMarginRatio = netProfitMarginRatio;
        this.currentRatio = currentRatio;
        this.quickRatio = quickRatio;
        this.debtToEquityRatio = debtToEquityRatio;
        this.debtDependency = debtDependency;
    }

}
