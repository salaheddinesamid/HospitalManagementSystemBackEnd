package com.hospitalmanagement.application.dto;

import com.hospitalmanagement.application.model.Doctor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Data

public class AppointmentDto {
    Integer userId;
    Integer doctor;
    String disease;
    Date date;
    Time time;
    String status;
    Integer roomNumber;

}
