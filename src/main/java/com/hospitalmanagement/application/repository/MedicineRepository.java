package com.hospitalmanagement.application.repository;

import com.hospitalmanagement.application.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<Medicine,Integer> {
}
