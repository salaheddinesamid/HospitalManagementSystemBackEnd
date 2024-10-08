package com.hospitalmanagement.application.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Entity
@Getter
@Setter
public class Appointment {
    @Id
    @GeneratedValue
    Integer id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    Doctor doctor;

    @Column(name = "date")
    Date date;

    @Column(name = "time")
    Time time;

    @Column(name = "status")
    String status;
}
