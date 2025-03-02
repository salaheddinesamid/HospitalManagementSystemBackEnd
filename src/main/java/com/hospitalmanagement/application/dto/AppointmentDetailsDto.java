package com.hospitalmanagement.application.dto;

import com.hospitalmanagement.application.model.Doctor;
import com.hospitalmanagement.application.model.Patient;
import lombok.Data;

import java.util.Date;

@Data
public class AppointmentDetailsDto {

    Integer appointmentId;
    String disease;
    Doctor diagnosedBy;
    Patient patient;
    Date date;
    String location;
    String status;

}
