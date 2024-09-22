package com.hospitalmanagement.application.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Nurse {

    @Id
    @GeneratedValue
    Integer id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;
}
