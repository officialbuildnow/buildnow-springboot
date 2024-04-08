package com.buildnow.springbootapp.buildnowspringboot.dto.applicationEvaluation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationEvaluationListDTO {
    @JsonProperty("applicationEvaluationDTOList")
    private List<ApplicationEvaluationDTO> applicationEvaluationDTOList;
}
