package com.hospitalmanagement.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    boolean existsByEmail(String email);
    User findByEmail(String email);
    User findByNationalId(String nationalId);
}
