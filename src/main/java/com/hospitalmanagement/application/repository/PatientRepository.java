package com.hospitalmanagement.application.repository;

import com.hospitalmanagement.application.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Integer> {
    boolean existsByFirstNameAndLastName(String firstName,String lastName);
    Patient findByNationalId(String nationalId);
    Patient findByEmail(String email);

    //Check user existence
    boolean existsByEmail(String email);

    Patient findByFirstName(String firstName);
    boolean existsByNationalId(String nationalId);
}
