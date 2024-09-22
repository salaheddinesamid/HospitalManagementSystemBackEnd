package com.hospitalmanagement.application.repository;

import com.hospitalmanagement.application.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
}
