package com.hospitalmanagement.application.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ContactPatient {

    @Id
    @GeneratedValue
    Integer id;

    @OneToOne
    @JoinColumn(name = "contact_id")
    Contact contact;

    @OneToOne
    @JoinColumn(name = "patient_id")
    Patient patient;
}
