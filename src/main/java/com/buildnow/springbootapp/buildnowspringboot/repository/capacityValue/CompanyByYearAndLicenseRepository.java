package com.buildnow.springbootapp.buildnowspringboot.repository.capacityValue;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.capacityValue.CompanyByYearAndLicense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyByYearAndLicenseRepository extends JpaRepository<CompanyByYearAndLicense, Long> {
}
