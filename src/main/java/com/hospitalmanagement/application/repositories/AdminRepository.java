package com.hospitalmanagement.application.repositories;

import com.hospitalmanagement.application.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin,Integer> {
    Optional<Admin> findByEmail(String email);

    Boolean existsByEmail(String email);
}
