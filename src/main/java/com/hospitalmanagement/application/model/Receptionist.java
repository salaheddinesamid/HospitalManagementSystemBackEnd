package com.hospitalmanagement.application.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.service.annotation.GetExchange;

import java.util.Date;

@Entity
@Getter
@Setter
public class Receptionist {

    @Id
    @GeneratedValue
    Integer id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "badge_number")
    String badgeNumber;

    @Column(name = "joined_from")
    Date joinedFrom;

    @Column(name = "professional_email")
    String email;

}
