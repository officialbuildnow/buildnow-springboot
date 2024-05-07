package com.buildnow.springbootapp.buildnowspringboot.dto.recruiter;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class RecruitmentInfoDTO {
    private Long recruitmentId;
    private String recruitmentTitle;
    private LocalDate deadLine;
    private Long threshold;
    private Long applicationNum;
}

