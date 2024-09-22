package com.hospitalmanagement.application.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class MedicalRecord {

    @Id
    @GeneratedValue
    Integer id;

    @ManyToOne
    @JoinColumn(name = "patient")
    Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor")
    Doctor doctor;

    @Column(name = "diagnosis")
    String diagnosis;

    @Column(name = "test_result",nullable = true)
    String testResult;

    @Column(name = "date")
    Date date;

    @Column(name = "notes")
    String notes;
}
