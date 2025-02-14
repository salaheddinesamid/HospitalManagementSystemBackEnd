package com.hospitalmanagement.application.dto;


import com.hospitalmanagement.application.model.Doctor;
import com.hospitalmanagement.application.model.Patient;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Data
@Getter
@Setter
public class MedicalRecordDto {

    // Patient personal Information:
    Patient patient;
    // Doctor information
    Doctor doctor;
    // Health information
    String diagnosis;
    String testResult;
    Date date;
    String notes;
}
