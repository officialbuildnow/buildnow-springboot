package com.buildnow.springbootapp.buildnowspringboot.repository;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.Applier;
import com.buildnow.springbootapp.buildnowspringboot.entitiy.applierInfo.License;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LicenseRepository extends JpaRepository<License, Long> {
    License findByLicenseSeq(String licenseSeq);
    License findByLicenseSeqAndApplier(String licenseSeq, Applier applier);
    boolean existsByLicenseNameAndApplier(String licenseName, Applier applier);
}