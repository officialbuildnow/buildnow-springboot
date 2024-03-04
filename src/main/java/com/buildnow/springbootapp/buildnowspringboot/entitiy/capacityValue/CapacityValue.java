package com.buildnow.springbootapp.buildnowspringboot.entitiy.capacityValue;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class CapacityValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int year;
    private String patentName;

    @OneToMany(mappedBy = "capacityValue" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CompanyByYearAndPatent> companyByYearAndPatentList;
}
