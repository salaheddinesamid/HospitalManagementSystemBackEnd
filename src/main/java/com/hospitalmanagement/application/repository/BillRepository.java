package com.hospitalmanagement.application.repository;

import com.hospitalmanagement.application.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill,Integer> {

    List<Bill> findAllByStatus(String status);
    List<Bill> findAllByPatient_Id(Integer id);
}
