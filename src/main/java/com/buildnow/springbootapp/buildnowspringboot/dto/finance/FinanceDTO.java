package com.buildnow.springbootapp.buildnowspringboot.dto.finance;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinanceDTO {
    @JsonProperty("category")
    private String category;
    @JsonProperty("value")
    private String value;
}
