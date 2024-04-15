package com.buildnow.springbootapp.buildnowspringboot.dto.CapacityValue;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CapacityValueOutputDTO {
    private Long totalCapacityValue;
    private Long rank;
    private Long percentage;

    @Builder
    public CapacityValueOutputDTO(
            Long totalCapacityValue,
            Long rank,
            Long percentage
    ){
        this.totalCapacityValue = totalCapacityValue;
        this.rank = rank;
        this.percentage = percentage;
    }
}
