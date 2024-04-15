package com.buildnow.springbootapp.buildnowspringboot.controller;

import com.buildnow.springbootapp.buildnowspringboot.dto.CapacityValue.CapacityValueInputDTO;
import com.buildnow.springbootapp.buildnowspringboot.dto.CapacityValue.CapacityValueInputListDTO;
import com.buildnow.springbootapp.buildnowspringboot.dto.CapacityValue.CapacityValueOutputDTO;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.capacityValue.CapacityValue;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.capacityValue.CompanyByYearAndLicense;
import com.buildnow.springbootapp.buildnowspringboot.repository.capacityValue.CapacityValueRepository;
import com.buildnow.springbootapp.buildnowspringboot.repository.capacityValue.CompanyByYearAndLicenseRepository;
import com.buildnow.springbootapp.buildnowspringboot.service.CapacityValueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/capacity-value")
public class CapacityValueController {
    private final CapacityValueRepository capacityValueRepository;
    private final CompanyByYearAndLicenseRepository companyByYearAndLicenseRepository;
    private final CapacityValueService capacityValueService;

    @DeleteMapping("/admin/delete-all-capacity-value")
    public ResponseEntity<String> clearCapacityValue(){
        capacityValueService.clearCapacityValue();
        return new ResponseEntity<>("삭제 완료", HttpStatus.OK);
    }

    @PostMapping("/admin/create-capacity-value")
    public ResponseEntity<String> createCapacityValue(@RequestParam("licenseName") String licenseName, @RequestParam("year") int year, @RequestBody CapacityValueInputListDTO capacityValueInputDTOList) throws IOException {
        CapacityValue res = capacityValueService.insertJSONToDB(licenseName, year, capacityValueInputDTOList);
        return new ResponseEntity<>(res.getCompanyByYearAndLicenseList().size() + " 개의 " + licenseName + " 데이터 입력 완료", HttpStatus.CREATED);
    }
    @GetMapping("/admin&recruiter/get-capacity-value")
    public ResponseEntity<CapacityValueOutputDTO> findCapacityValueByLicenseNum(@RequestParam("licenseNum") String licenseNum, @RequestParam("year") int year, @RequestParam("licenseName") String licenseName){
        CapacityValue capacityValue = capacityValueRepository.findByLicenseNameAndYear(licenseName, year);
        List<CompanyByYearAndLicense> companyByYearAndLicenseList = capacityValue.getCompanyByYearAndLicenseList();

        if(companyByYearAndLicenseList.isEmpty()){
            throw new RuntimeException("해당하는 면허가 존재하지 않습니다.");
        }

        sortCompaniesByTotalCapacityValue(companyByYearAndLicenseList);
        long rank = 0L;
        for(long i = 0L; i<companyByYearAndLicenseList.size(); i++){
            if(companyByYearAndLicenseList.get((int) i).getLicenseNum().equals(licenseNum)){
                rank = (long) (i+1);

                CapacityValueOutputDTO res = CapacityValueOutputDTO.builder()
                        .totalCapacityValue(companyByYearAndLicenseList.get((int) i).getTotalCapacityValue())
                        .percentage(rank * 100 / companyByYearAndLicenseList.size())
                        .rank(rank)
                        .build();
                return new ResponseEntity<>(res, HttpStatus.OK);
            }
        }
        throw new RuntimeException("Error Occurred");
    }

    public void sortCompaniesByTotalCapacityValue(List<CompanyByYearAndLicense> companyList) {
        companyList.sort((company1, company2) -> {
            return Comparator.comparingLong(CompanyByYearAndLicense::getTotalCapacityValue)
                    .reversed()
                    .compare(company1, company2);
        });
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeExceptionHandler(RuntimeException ex){
        return new ResponseEntity<>("Error Occurred: " + ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
