package com.hospitalmanagement.application.repository;

import com.hospitalmanagement.application.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Integer> {

    boolean existsByEmail(String email);
    Admin findByEmail(String email);
}
