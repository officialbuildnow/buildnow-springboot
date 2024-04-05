package com.buildnow.springbootapp.buildnowspringboot.dto.tempSave;

import com.buildnow.springbootapp.buildnowspringboot.ENUM.BusinessTypeENUM;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.TempHandedOut;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TempSavingDTO {
    @JsonProperty("corporateApplication")
    private String corporateApplication;
    @JsonProperty("companyPhoneNum")
    private String companyPhoneNum;
    @JsonProperty("workTypeApplying")
    private String workTypeApplying;
    @JsonProperty("type")
    private BusinessTypeENUM type;
    @JsonProperty("companyAddress")
    private String companyAddress;
    @Lob
    @JsonProperty("companyIntro")
    private String companyIntro;
    @JsonProperty("tempHandedOutList")
    private List<TempHandedOut> tempHandedOutList;
}
