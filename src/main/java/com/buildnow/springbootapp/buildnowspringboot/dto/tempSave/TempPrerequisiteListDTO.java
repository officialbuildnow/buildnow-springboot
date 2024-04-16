package com.buildnow.springbootapp.buildnowspringboot.dto.tempSave;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TempPrerequisiteListDTO {
    private List<TempPrerequisiteDTO> tempPrerequisiteDTOList;
}
