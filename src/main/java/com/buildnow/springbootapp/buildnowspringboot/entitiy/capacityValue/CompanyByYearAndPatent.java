package com.buildnow.springbootapp.buildnowspringboot.entitiy.capacityValue;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CompanyByYearAndPatent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;
    private String patentId;
    private String ceoName;
    private String location;
    private Long mechanicNum;
    private Long totalCapacityValue;
    private Long constructionCapacityValue;
    private Long businessCapacityValue;
    private Long creditWorthinessCapacityValue;

    @ManyToOne
    private CapacityValue capacityValue;
}
