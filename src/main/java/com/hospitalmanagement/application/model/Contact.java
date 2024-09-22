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
public class Contact {

    @Id
    @GeneratedValue
    Integer id;

    @Column(name = "phone")
    String phone;

    @Column(name = "email")
    String email;
}
