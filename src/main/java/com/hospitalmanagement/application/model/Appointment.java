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
    @JoinColumn(name = "user")
    User user;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    Doctor doctor;

    @Column(name = "disease")
    String disease;

    @Column(name = "date")
    Date date;

    @Column(name = "time")
    Time time;

    @Column(name = "status")
    String status;
}
