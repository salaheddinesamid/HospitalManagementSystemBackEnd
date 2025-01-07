package com.hospitalmanagement.application.dto;

import com.hospitalmanagement.application.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Emergency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer emergencyId;

    @Column(name = "date")
    Date date;

    @Column(name = "location")
    String location;

    @OneToOne
    @JoinColumn(name = "")
    User user;
}
