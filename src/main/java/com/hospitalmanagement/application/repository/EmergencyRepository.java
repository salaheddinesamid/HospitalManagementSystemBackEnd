package com.hospitalmanagement.application.repository;

import com.hospitalmanagement.application.dto.Emergency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface EmergencyRepository extends JpaRepository<Emergency,Integer> {
    List<Emergency> findAllByDate(Date date);
}
