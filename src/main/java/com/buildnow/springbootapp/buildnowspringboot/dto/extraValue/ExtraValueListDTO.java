package com.buildnow.springbootapp.buildnowspringboot.dto.extraValue;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ExtraValueListDTO {
    private List<ExtraValueDTO> extraValueList;
}
