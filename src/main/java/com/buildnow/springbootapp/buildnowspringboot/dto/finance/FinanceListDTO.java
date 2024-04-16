package com.buildnow.springbootapp.buildnowspringboot.dto.finance;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class FinanceListDTO {
    private List<FinanceDTO> financeList;
}
