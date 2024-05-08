package com.hospitalmanagement.application.repositories;

import com.hospitalmanagement.application.models.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NurseRepository extends JpaRepository<Nurse,Long> {
}
