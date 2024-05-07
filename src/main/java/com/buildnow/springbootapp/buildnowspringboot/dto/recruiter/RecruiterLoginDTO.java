package com.buildnow.springbootapp.buildnowspringboot.dto.recruiter;

import com.buildnow.springbootapp.buildnowspringboot.dto.RecruitingWorkType.RecruitingWorkTypeListDTO;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RecruiterLoginDTO {
    private String recruiterName;
    private String recruiterLogo;
    private List<RecruitmentInfoDTO> recruitmentInfoDTOList;
}
