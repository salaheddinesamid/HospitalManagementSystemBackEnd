package com.hospitalmanagement.application.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.print.Doc;
import java.util.Date;

@Entity
@Getter
@Setter
public class AppointmentReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer appointmentReportId;

    @ManyToOne
    User user;

    @OneToOne
    Doctor doctor;

    @OneToOne
    Appointment appointment;

    @Column(name = "started_at")
    Date startedAt;

    @Column(name = "finished_at")
    Date finishedAt;

    @Column(name = "status")
    String status;

    @Column(name = "notes")
    String notes;
}
