package com.hospitalmanagement.application.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "specialization")
    String specialization;

    @Column(name = "age")
    Integer age;

    @Column(name = "years_of_experience")
    Integer yearsOfExperience;

    @Column(name = "nationalId")
    String nationalId;

    @Column(name = "professionalId")
    String professionalId;

    @Column(name = "date_of_birth")
    Date birth;
}
