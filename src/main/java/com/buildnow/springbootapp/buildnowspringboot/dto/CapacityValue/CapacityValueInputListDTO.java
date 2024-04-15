package com.buildnow.springbootapp.buildnowspringboot.dto.CapacityValue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CapacityValueInputListDTO {
    private List<CapacityValueInputDTO> capacityValueInputDTOList;
}
