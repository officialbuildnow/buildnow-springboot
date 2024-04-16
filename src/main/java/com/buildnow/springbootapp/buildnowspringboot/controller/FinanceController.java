package com.buildnow.springbootapp.buildnowspringboot.controller;

import com.buildnow.springbootapp.buildnowspringboot.dto.finance.FinanceDTO;
import com.buildnow.springbootapp.buildnowspringboot.dto.finance.FinanceListDTO;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.Applier;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.applierInfo.Finance;
import com.buildnow.springbootapp.buildnowspringboot.repository.ApplierRepository;
import com.buildnow.springbootapp.buildnowspringboot.service.FinanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/finance")
public class FinanceController {
    private final FinanceService financeService;
    private final ApplierRepository applierRepository;
    @PostMapping("/admin/{id}")
    public ResponseEntity<List<Finance>> insertFinanceInfo(@PathVariable("id") Long applicationId,
                                                           @RequestBody FinanceListDTO financeListDTO){
        List<Finance> res = new ArrayList<>();
        for(FinanceDTO financeInfo : financeListDTO.getFinanceList()){
            Finance finance = financeService.createFinanceTuple(applicationId, financeInfo.getCategory(), financeInfo.getValue());
            res.add(finance);
        }

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeExceptionHandler(RuntimeException ex){
        return new ResponseEntity<>("Error Occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
