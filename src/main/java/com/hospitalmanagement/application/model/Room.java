package com.hospitalmanagement.application.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Room {

    @Id
    @GeneratedValue
    Integer id;

    @Column(name = "room_number")
    Integer roomNumber;


    @Column(name = "max_capacity",nullable = true)
    Integer capacity;

    @Column(name = "available_from",nullable = true)
    Date availableFrom;

    @Column(name = "status",nullable = true)
    String status;

    @Column(name = "price_per_day")
    Integer pricePerDay;

    @OneToOne
    @JoinColumn(name = "patient_id", nullable = true)
    Patient patient;

    public Room(Integer roomNumber){
        this.roomNumber = roomNumber;
        this.status = status;
    }

    public Room() {

    }
}
