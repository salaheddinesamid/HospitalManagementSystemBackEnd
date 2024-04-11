package com.hospitalmanagement.application.repositories;

import com.hospitalmanagement.application.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    boolean existsByFullName(String fullName);


}
