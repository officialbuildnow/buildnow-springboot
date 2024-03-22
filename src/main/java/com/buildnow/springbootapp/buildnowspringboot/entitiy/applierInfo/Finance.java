package com.buildnow.springbootapp.buildnowspringboot.entitiy.applierInfo;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.Applier;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
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

    @OneToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private Applier applier;
}
