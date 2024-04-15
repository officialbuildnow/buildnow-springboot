package com.buildnow.springbootapp.buildnowspringboot.dto.applier;

import com.buildnow.springbootapp.buildnowspringboot.dto.applicationEvaluation.ScoreResponseListDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ApplierWithScoreDTO {
    private boolean isRead;
    private boolean isChecked;
    private Long applicationId;
    private String companyName;
    private String workType;
    private List<ScoreResponseListDTO> scoreList;
}
