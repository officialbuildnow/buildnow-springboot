package com.buildnow.springbootapp.buildnowspringboot.dto.RecruitingWorkType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecruitingWorkTypeDTO {
    private String recruitingWorkType;
    private PatentListByRecruitingWorkType patentListByRecruitingWorkType;
}
