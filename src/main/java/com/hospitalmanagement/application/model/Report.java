package com.hospitalmanagement.application.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer reportId;


    @Column(name = "subject")
    String subject;

    @Column(name = "date")
    Date date;

    @OneToOne
    @JoinColumn(name = "user")
    User user;
}
