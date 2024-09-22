package com.hospitalmanagement.application.repository;

import com.hospitalmanagement.application.model.ContactDoctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactDoctorRepository extends JpaRepository<ContactDoctor,Integer> {
}
