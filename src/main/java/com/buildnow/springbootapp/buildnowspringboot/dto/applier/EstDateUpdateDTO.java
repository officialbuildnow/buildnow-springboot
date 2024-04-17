package com.buildnow.springbootapp.buildnowspringboot.dto.applier;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class EstDateUpdateDTO {
    @JsonProperty("estDate")
    private LocalDate estDate;
}
