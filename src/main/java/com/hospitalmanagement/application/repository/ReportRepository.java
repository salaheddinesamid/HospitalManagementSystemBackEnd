package com.hospitalmanagement.application.repository;

import com.hospitalmanagement.application.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report,Integer> {
}
