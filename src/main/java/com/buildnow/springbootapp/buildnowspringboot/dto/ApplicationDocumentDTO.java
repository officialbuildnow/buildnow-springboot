package com.buildnow.springbootapp.buildnowspringboot.dto;

import com.buildnow.springbootapp.buildnowspringboot.ENUM.BusinessTypeENUM;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
// 협력업체신청서를 제출하면 전달되는 요청틀
public class ApplicationDocumentDTO {
    private String documentName;
    private String documentURL;
    private String corporateApplicationNum;
    private String companyPhoneNum;
    private String workTypeApplying;
    private String patent1Name;
    private String patent2Name;
    private String patent3Name;

    public ApplicationDocumentDTO(
            @JsonProperty("documentURL") String documentURL,
            @JsonProperty("corporateApplicationNum") String corporateApplicationNum,
            @JsonProperty("companyPhoneNum") String companyPhoneNum,
            @JsonProperty("workTypeApplying") String workTypeApplying,
            @JsonProperty("patent1Name") String patent1Name,
            @JsonProperty("patent2Name") String patent2Name,
            @JsonProperty("patent3Name") String patent3Name
    ){
        this.documentName = "협력업체신청서";
        this.documentURL = documentURL;
        this.corporateApplicationNum = corporateApplicationNum;
        this.companyPhoneNum = companyPhoneNum;
        this.workTypeApplying = workTypeApplying;
        this.patent1Name = patent1Name;
        this.patent2Name = patent2Name;
        this.patent3Name = patent3Name;
    }

}
