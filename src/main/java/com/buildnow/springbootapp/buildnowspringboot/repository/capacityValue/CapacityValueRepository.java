package com.buildnow.springbootapp.buildnowspringboot.repository.capacityValue;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.capacityValue.CapacityValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CapacityValueRepository extends JpaRepository<CapacityValue, Long> {
    CapacityValue findByLicenseNameAndYear(String licenseName, int year);
}
