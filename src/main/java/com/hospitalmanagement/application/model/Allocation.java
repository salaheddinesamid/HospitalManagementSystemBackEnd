package com.hospitalmanagement.application.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Allocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer allocationId;

    @Column(name = "room_number")
    Integer roomNumber;

    @OneToOne
    @Column(name = "patient")
    Patient patient;
}
