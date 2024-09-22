package com.hospitalmanagement.application.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ContactDoctor {

    @Id
    @GeneratedValue
    Integer id;

    @OneToOne
    @JoinColumn(name = "contact_id")
    Contact contact;

    @OneToOne
    @JoinColumn(name = "doctor_id")
    Doctor doctor;
}
