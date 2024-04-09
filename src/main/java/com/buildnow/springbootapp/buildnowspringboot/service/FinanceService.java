package com.buildnow.springbootapp.buildnowspringboot.service;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.Applier;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.applierInfo.Finance;
import com.buildnow.springbootapp.buildnowspringboot.repository.ApplierRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.FinanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FinanceService {
    private final FinanceRepository financeRepository;
    private final ApplierRepository applierRepository;
    @Transactional
    public Finance createFinanceTuple(String businessId,
                                            String category,
                                            String value){
        Applier applier = applierRepository.findByBusinessId(businessId);
        Finance newFinance = Finance.builder()
                .category(category)
                .value(value)
                .build();
        applier.addFinance(newFinance);
        return financeRepository.save(newFinance);
    }
}
