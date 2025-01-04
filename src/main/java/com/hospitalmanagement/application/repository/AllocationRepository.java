package com.hospitalmanagement.application.repository;

import com.hospitalmanagement.application.model.Allocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllocationRepository extends JpaRepository<Allocation,Integer> {
}
