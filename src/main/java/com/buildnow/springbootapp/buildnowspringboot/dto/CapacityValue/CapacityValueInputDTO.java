package com.buildnow.springbootapp.buildnowspringboot.dto.CapacityValue;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CapacityValueInputDTO {
    @JsonProperty("Id")
    private Long id;
    @JsonProperty("상호")
    private String companyName;
    @JsonProperty("대표자")
    private String ceoName;
    @JsonProperty("소재지")
    private String location;
    @JsonProperty("전화번호")
    private String companyPhoneNum;
    @JsonProperty("등록번호")
    private String licenseNum;
    @JsonProperty("기술자 수")
    private Long mechanicNum;
    @JsonProperty("총액")
    private Long totalCapacityValue;
    @JsonProperty("공사실적\n평가액")
    private Long constructionCapacityValue;
    @JsonProperty("경영\n평가액")
    private Long businessCapacityValue;
    @JsonProperty("신인도\n평가액")
    private Long creditWorthinessCapacityValue;
    @JsonProperty("기술능력\n평가액")
    private Long techCapacityValue;
    @JsonProperty("(직전년도) 건설공사\n실적")
    private Long lastYearPerformance;
    @JsonProperty("비고")
    private String etc;
}
