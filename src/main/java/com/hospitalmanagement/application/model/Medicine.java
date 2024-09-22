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
public class Medicine {

    @Id
    @GeneratedValue
    Integer id;

    @Column(name = "medicine_name")
    String name;

    @Column(name = "stock")
    Integer stock;

    @Column(name = "expiry_date")
    Date date;
    


}
