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


    @ManyToOne
    Patient patient;

    @OneToOne
    Room room;

    @Column(name = "status")
    String status;

}
