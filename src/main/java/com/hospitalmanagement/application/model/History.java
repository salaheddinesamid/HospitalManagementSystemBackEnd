package com.hospitalmanagement.application.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer historyId;

    @Column(name = "date")
    Date date;

    @OneToOne
    @Column(name = "by")
    Patient patient;

    @Column(name = "action")
    String action;
}
