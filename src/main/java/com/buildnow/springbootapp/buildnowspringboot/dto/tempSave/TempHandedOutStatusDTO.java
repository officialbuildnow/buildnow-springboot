package com.buildnow.springbootapp.buildnowspringboot.dto.tempSave;

import com.buildnow.springbootapp.buildnowspringboot.ENUM.HandedOutVerifyingStatusENUM;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TempHandedOutStatusDTO {
    @JsonProperty("documentName")
    private String documentName;
    @JsonProperty("status")
    private HandedOutVerifyingStatusENUM handedOutVerifyingStatusENUM;
}
