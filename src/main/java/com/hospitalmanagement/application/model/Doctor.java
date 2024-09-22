package com.hospitalmanagement.application.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Doctor {

    @Id
    @GeneratedValue
    Integer id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "specialization")
    String specialization;

    @OneToOne
    @JoinColumn(name = "contact")
    ContactDoctor contactDoctor;


    @OneToOne
    @JoinColumn(name = "user_id")
    User user;
}
