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

    public MedicalRecord(Patient patient,Doctor doctor,String diagnosis,String testResult, Date date,String notes){
        this.patient = patient;
        this.doctor = doctor;
        this.diagnosis = diagnosis;
        this.date = date;
        this.testResult = testResult;
        this.notes = notes;

    }
}
