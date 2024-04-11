package com.hospitalmanagement.application.repositories;

import com.hospitalmanagement.application.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    boolean existsByNationalId(String nationalId);
}
