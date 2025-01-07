package com.hospitalmanagement.application.repository;

import com.hospitalmanagement.application.dto.Emergency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmergencyRepository extends JpaRepository<Emergency,Integer> {

}
