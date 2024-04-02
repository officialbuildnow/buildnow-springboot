package com.buildnow.springbootapp.buildnowspringboot.controller;

import com.buildnow.springbootapp.buildnowspringboot.dto.TempOCRDTO;
import com.buildnow.springbootapp.buildnowspringboot.dto.TempOCRListDTO;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.TempOCR;
import com.buildnow.springbootapp.buildnowspringboot.service.TempOCRService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tempOCR")
public class TempOCRController {
    private final TempOCRService tempOCRService;

    @PostMapping("/{id}")
    public ResponseEntity<List<TempOCR>> uploadOCR(@PathVariable("id") Long applicationId,
                                                   Authentication authentication,
                                                   TempOCRListDTO tempOCRListDTO
    ){
        List<TempOCR> res = new ArrayList<>();
        for(TempOCRDTO info : tempOCRListDTO.getInfoList()){
            TempOCR tempOCR = tempOCRService.createTempOCR(authentication.getName(),
                    applicationId,
                    info.getCategory(),
                    info.getValue()
                    );
            res.add(tempOCR);
        }

        return new ResponseEntity<>(res, HttpStatus.CREATED);

    }
}
