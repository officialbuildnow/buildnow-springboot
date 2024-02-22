package com.buildnow.springbootapp.buildnowspringboot.entitiy.applierInfo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class CapacityValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long year1Value;
    private Long year2Value;
    private Long year3Value;
    private Long nationalRanking;
    private Long regionalRanking;
    private Long nationalRankingRatio;
    private Long regionalRankingRatio;
    @ManyToOne
    private PossibleWorkType possibleWorkType;
}
