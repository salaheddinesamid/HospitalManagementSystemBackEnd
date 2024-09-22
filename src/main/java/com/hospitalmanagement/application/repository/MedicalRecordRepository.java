package com.hospitalmanagement.application.repository;

import com.hospitalmanagement.application.model.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord,Integer> {
}
