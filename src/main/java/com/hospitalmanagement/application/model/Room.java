package com.hospitalmanagement.application.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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


    @Column(name = "max_capacity")
    Integer capacity;

    @Column(name = "available_from")
    Date availableFrom;

    @Column(name = "status")
    String status;
}
