package com.buildnow.springbootapp.buildnowspringboot.dto.applier;

import com.buildnow.springbootapp.buildnowspringboot.dto.applicationEvaluation.ScoreResponseListDTO;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.TempPrerequisite;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ApplierWithScoreDTO {
    private boolean isRead;
    private boolean isChecked;
    private boolean isAdminChecked;
    private Long applicationId;
    private String companyName;
    private String workType;
    private List<TempPrerequisite> tempPrerequisiteList;
    private List<ScoreResponseListDTO> scoreList;
}
