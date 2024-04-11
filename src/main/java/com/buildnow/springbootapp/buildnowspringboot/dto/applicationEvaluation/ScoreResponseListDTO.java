package com.buildnow.springbootapp.buildnowspringboot.dto.applicationEvaluation;

import com.buildnow.springbootapp.buildnowspringboot.ENUM.UpperCategoryENUM;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreResponseListDTO {
    private UpperCategoryENUM upperCategory;
    private Long upperCategoryPerfectScore;
    private Long upperCategoryScore;
    private List<ScoreResponseDTO> scoreList;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ScoreResponseDTO {
        private String category;
        private Long perfectScore;
        private Long score;
    }
}
