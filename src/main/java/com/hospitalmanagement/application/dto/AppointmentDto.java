package com.hospitalmanagement.application.dto;

import com.hospitalmanagement.application.model.Doctor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Data
@Getter
@Setter
public class AppointmentDto {

    String firstName;
    String lastName;
    String nationalId;
    String email;
    String phone;
    Doctor doctor;
    Date date;
    Time time;
    String status;
    String address;
    Integer roomNumber;

}
