package com.hospitalmanagement.application.repository;

import com.hospitalmanagement.application.model.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History,Integer> {
}
