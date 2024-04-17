package com.buildnow.springbootapp.buildnowspringboot.dto.applier;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HadAccidentUpdateDTO {
    @JsonProperty("hadAccident")
    private Boolean hadAccident;
}
