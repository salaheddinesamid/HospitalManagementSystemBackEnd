package com.hospitalmanagement.application.departments.staff_management;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    boolean existsByFullName(String fullName);


}
