package com.buildnow.springbootapp.buildnowspringboot.dto.applier;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ApplierWithScoreListDTO {
    private List<ApplierWithScoreDTO> applierWithScoreDTOList;
}
