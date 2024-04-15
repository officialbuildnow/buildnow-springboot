package com.buildnow.springbootapp.buildnowspringboot.service;

import com.buildnow.springbootapp.buildnowspringboot.dto.CapacityValue.CapacityValueInputDTO;
import com.buildnow.springbootapp.buildnowspringboot.dto.CapacityValue.CapacityValueInputListDTO;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.capacityValue.CapacityValue;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.capacityValue.CompanyByYearAndLicense;
import com.buildnow.springbootapp.buildnowspringboot.repository.capacityValue.CapacityValueRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.capacityValue.CompanyByYearAndLicenseRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CapacityValueService {
    private final CapacityValueRepository capacityValueRepository;
    private final CompanyByYearAndLicenseRepository companyByYearAndLicenseRepository;

    @Transactional
    public void clearCapacityValue(){
        capacityValueRepository.deleteAll();
    }

    @Transactional
    public CapacityValue insertJSONToDB(String licenseName, int year, CapacityValueInputListDTO capacityValueInputListDTO) throws IOException {
        CapacityValue capacityValue = CapacityValue.builder()
                .year(year)
                .licenseName(licenseName)
                .build();
        List<CapacityValueInputDTO> companies = capacityValueInputListDTO.getCapacityValueInputDTOList();

        for(CapacityValueInputDTO company : companies) {

            CompanyByYearAndLicense companyByYearAndLicense = CompanyByYearAndLicense.builder()
                    .companyName(company.getCompanyName())
                    .ceoName(company.getCeoName())
                    .location(company.getLocation())
                    .companyPhoneNum(company.getCompanyPhoneNum())
                    .licenseNum(company.getLicenseNum())
                    .totalCapacityValue(company.getTotalCapacityValue())
                    .constructionCapacityValue(company.getConstructionCapacityValue())
                    .businessCapacityValue(company.getBusinessCapacityValue())
                    .techCapacityValue(company.getTechCapacityValue())
                    .creditWorthinessCapacityValue(company.getCreditWorthinessCapacityValue())
                    .lastYearPerformance(company.getLastYearPerformance())
                    .mechanicNum(company.getMechanicNum())
                    .build();
            capacityValue.addCompanyByYearAndLicense(companyByYearAndLicense);

        }
        return capacityValueRepository.save(capacityValue);
    }

}
