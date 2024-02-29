package com.buildnow.springbootapp.buildnowspringboot.repository;

import com.buildnow.springbootapp.buildnowspringboot.entitiy.Applier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplierRepository extends JpaRepository<Applier, Long> {
    Boolean existsByUsername(String username);

    Boolean existsByBusinessId(String businessId);
    Applier findByUsername(String username);
}
