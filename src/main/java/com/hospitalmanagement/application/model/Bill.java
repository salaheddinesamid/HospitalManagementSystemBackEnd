package com.hospitalmanagement.application.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Bill {
    @Id
    @GeneratedValue
    Integer id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    Patient patient;

    @Column(name = "amount")
    Integer amount;

    @Column(name = "status")
    String status;

    @Column(name = "date")
    Date date;


   @OneToOne
    Appointment appointment;
}
