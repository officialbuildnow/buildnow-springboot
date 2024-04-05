package com.buildnow.springbootapp.buildnowspringboot.controller;

import com.buildnow.springbootapp.buildnowspringboot.dto.tempSave.TempOCRDTO;
import com.buildnow.springbootapp.buildnowspringboot.dto.tempSave.TempOCRListDTO;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.TempOCR;
import com.buildnow.springbootapp.buildnowspringboot.repository.ApplicationRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.tempSave.TempOCRRepository;
import com.buildnow.springbootapp.buildnowspringboot.service.TempOCRService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/tempOCR")
public class TempOCRController {
    private final ApplicationRepository applicationRepository;
    private final TempOCRService tempOCRService;
    private final TempOCRRepository tempOCRRepository;
    @PostMapping("/applier/{id}")
    public ResponseEntity<List<TempOCR>> uploadOCR(@PathVariable("id") Long applicationId,
                                                   Authentication authentication,
                                                   TempOCRListDTO tempOCRListDTO
    ){
        log.info(tempOCRListDTO.toString());
        List<TempOCR> res = new ArrayList<>();
        for(TempOCRDTO info : tempOCRListDTO.getInfoList()){
            TempOCR tempOCR = tempOCRService.createTempOCR(authentication.getName(),
                    applicationId,
                    info.getCategory(),
                    info.getValue()
                    );

            res.add(tempOCRRepository.save(tempOCR));
        }

        return new ResponseEntity<>(res, HttpStatus.CREATED);

    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<List<TempOCR>> getTempOCRList(@PathVariable("id") Long applicationId){
        List<TempOCR> res = tempOCRService.retrieveMyTempOCRs(applicationId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping()

    @PatchMapping("/admin/update/{id}")
    public ResponseEntity<String> updateTempOCRs(@PathVariable("id") Long applicationId, TempOCRListDTO tempOCRListDTO){
        tempOCRService.updateTempOCR(applicationId, tempOCRListDTO);
        return new ResponseEntity<>("tempOCR 업데이트 완료!",HttpStatus.OK);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeExceptionHandler(RuntimeException ex){
        return new ResponseEntity<>("Error Occurred: " + ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
