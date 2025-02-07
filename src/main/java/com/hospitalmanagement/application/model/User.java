package com.hospitalmanagement.application.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue
    Integer id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "email",nullable = true)
    String email;

    @Column(name = "password",nullable = true)
    String password;

    @Column(name = "national_id")
    String nationalId;

    @Column(name = "role")
    String role;


}
