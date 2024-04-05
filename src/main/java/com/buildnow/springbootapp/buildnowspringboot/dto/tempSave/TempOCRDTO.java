package com.buildnow.springbootapp.buildnowspringboot.dto.tempSave;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TempOCRDTO {
    @JsonProperty("category")
    private String category;
    @JsonProperty("value")
    private String value;
}
