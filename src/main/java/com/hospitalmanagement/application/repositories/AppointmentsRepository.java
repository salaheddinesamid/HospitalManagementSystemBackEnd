package com.hospitalmanagement.application.repositories;

import com.hospitalmanagement.application.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentsRepository extends JpaRepository<Appointment,Long> {

}
