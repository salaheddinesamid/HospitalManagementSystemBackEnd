package com.hospitalmanagement.application.repositories;

import com.hospitalmanagement.application.models.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiseaseRepository extends JpaRepository<Disease,Long> {
}
