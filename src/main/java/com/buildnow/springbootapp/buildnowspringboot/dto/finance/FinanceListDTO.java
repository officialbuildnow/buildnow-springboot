package com.buildnow.springbootapp.buildnowspringboot.dto.finance;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FinanceListDTO {
    @JsonProperty("financeList")
    private List<FinanceDTO> financeList;
}
