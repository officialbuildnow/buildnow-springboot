package com.buildnow.springbootapp.buildnowspringboot.dto.tempSave;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TempPrerequisiteDTO {
    @JsonProperty("prerequisiteName")
    private String prerequisiteName;
    @JsonProperty("isPrerequisite")
    private Boolean isPrerequisite;
    @JsonProperty("whyMidal")
    private String whyMidal;
}
