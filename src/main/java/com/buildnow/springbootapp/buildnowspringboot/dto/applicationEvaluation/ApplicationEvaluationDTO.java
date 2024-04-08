package com.buildnow.springbootapp.buildnowspringboot.dto.applicationEvaluation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationEvaluationDTO {
    @JsonProperty("categoryName")
    private String categoryName;
    @JsonProperty("score")
    private Long score;
}
