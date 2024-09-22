package com.hospitalmanagement.application.repository;

import com.hospitalmanagement.application.model.ContactPatient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactPatientRepository extends JpaRepository<ContactPatient,Integer> {
}
