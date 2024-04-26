package com.buildnow.springbootapp.buildnowspringboot.dto.RecruitingWorkType;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PatentListByRecruitingWorkType {
    private List<String> patentList;
}
