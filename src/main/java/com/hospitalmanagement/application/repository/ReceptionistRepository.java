package com.hospitalmanagement.application.repository;

import com.hospitalmanagement.application.model.Receptionist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceptionistRepository extends JpaRepository<Receptionist,Integer> {
}
