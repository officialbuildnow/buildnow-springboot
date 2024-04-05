package com.buildnow.springbootapp.buildnowspringboot.dto;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.TempHandedOut;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.TempSaved;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TempSavedResponseDTO {
    private TempSaved tempSaved;
    private TempHandedOut tempHandedOut;

    @Builder
    public TempSavedResponseDTO(TempSaved tempSaved, TempHandedOut tempHandedOut){
        this.tempSaved = tempSaved;
        this.tempHandedOut = tempHandedOut;
    }
}
