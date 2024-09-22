package com.hospitalmanagement.application.repository;

import com.hospitalmanagement.application.model.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NurseRepository extends JpaRepository<Nurse,Integer> {
}
