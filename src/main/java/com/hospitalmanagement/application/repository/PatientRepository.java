package com.hospitalmanagement.application.repository;

import com.hospitalmanagement.application.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Integer> {
    boolean existsByFirstNameAndLastName(String firstName,String lastName);
}
