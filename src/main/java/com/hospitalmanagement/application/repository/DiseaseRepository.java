package com.hospitalmanagement.application.repository;

import com.hospitalmanagement.application.model.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiseaseRepository extends JpaRepository<Disease,Integer> {
    Disease findByName(String name);
}
