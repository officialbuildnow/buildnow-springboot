package com.buildnow.springbootapp.buildnowspringboot.service;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.Applier;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.application.Application;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.applierInfo.Finance;
import com.buildnow.springbootapp.buildnowspringboot.repository.ApplicationRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.ApplierRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.FinanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FinanceService {
    private final FinanceRepository financeRepository;
    private final ApplierRepository applierRepository;
    private final ApplicationRepository applicationRepository;
    @Transactional
    public Finance createFinanceTuple(Long applicationId,
                                            String category,
                                            String value){
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(()->new RuntimeException("해당하는 application이 존재하지 않습니다."));
        Applier applier = application.getApplier();
        if(financeRepository.existsByApplierAndCategory(applier, category)){
            throw new RuntimeException("이미 존재하는 finance 정보이기 때문에 입력할 수 없습니다.");
        }
        Finance newFinance = Finance.builder()
                .category(category)
                .value(value)
                .build();
        applier.addFinance(newFinance);
        return financeRepository.save(newFinance);
    }

    @Transactional
    public void DeleteFinance(Long applicationId){
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(()->new RuntimeException("해당하는 application이 존재하지 않습니다."));
        Applier applier = application.getApplier();
        List<Finance> financeList = applier.getFinanceList();
        List<Finance> toDelete = new ArrayList<>(financeList);
        for(Finance finance : toDelete){
            applier.removeFinance(finance);
            financeRepository.delete(finance);
        }
    }
}
