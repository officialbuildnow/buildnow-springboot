package com.buildnow.springbootapp.buildnowspringboot.dto.extraValue;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExtraValueDTO {
    @JsonProperty("category")
    private String category;
    @JsonProperty("value")
    private String value;
}
