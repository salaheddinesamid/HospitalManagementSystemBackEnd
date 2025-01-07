package com.hospitalmanagement.application.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Disease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer diseaseId;

    @Column(name = "name")
    String name;

    @Column(name = "price")
    Integer price;
}
