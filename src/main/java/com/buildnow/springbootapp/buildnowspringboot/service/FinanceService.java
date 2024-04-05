package com.buildnow.springbootapp.buildnowspringboot.service;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.applierInfo.Finance;
import com.buildnow.springbootapp.buildnowspringboot.repository.FinanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FinanceService {
    private final FinanceRepository financeRepository;

//    @Transactional
//    public List<Finance> createFinanceTuple(){
//
//    }
}
