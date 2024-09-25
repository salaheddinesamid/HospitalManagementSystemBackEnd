package com.hospitalmanagement.application.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Patient {

    @Id
    @GeneratedValue
    Integer id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "national_id")
    String nationalId;

    @Column(name = "address")
    String address;

    public Patient(String firstName,String lastName,String nationalId,String address){
        this.firstName  = firstName;
        this.lastName = lastName;
        this.nationalId = nationalId;
        this.address = address;
    }
    public Patient(){

    }
}
