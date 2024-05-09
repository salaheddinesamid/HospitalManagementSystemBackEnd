package com.hospitalmanagement.application.departments.appointment_management;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentsRepository extends JpaRepository<Appointment,Long> {

}
