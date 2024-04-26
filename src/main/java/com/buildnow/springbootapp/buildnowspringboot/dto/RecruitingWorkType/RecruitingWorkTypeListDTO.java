package com.buildnow.springbootapp.buildnowspringboot.dto.RecruitingWorkType;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RecruitingWorkTypeListDTO {
    private List<RecruitingWorkTypeDTO> recruitingWorkTypeDTOs;
}
