package com.hospitalmanagement.application.repository;

import com.hospitalmanagement.application.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill,Integer> {
}
