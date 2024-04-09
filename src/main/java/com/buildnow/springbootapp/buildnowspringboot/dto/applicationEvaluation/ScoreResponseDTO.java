package com.buildnow.springbootapp.buildnowspringboot.dto.applicationEvaluation;

import com.buildnow.springbootapp.buildnowspringboot.ENUM.UpperCategoryENUM;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class ScoreResponseDTO {
    private Long score;
    private UpperCategoryENUM upperCategory;
    private String category;
}
