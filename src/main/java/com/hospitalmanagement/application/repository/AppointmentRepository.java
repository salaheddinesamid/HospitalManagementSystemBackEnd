package com.hospitalmanagement.application.repository;

import com.hospitalmanagement.application.model.Appointment;
import com.hospitalmanagement.application.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {
    void deleteByUser(User user);
}
