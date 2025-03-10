package com.hospitalmanagement.application.dto;

import com.hospitalmanagement.application.model.Room;
import lombok.Data;

import java.util.Date;

@Data
public class AllocationResponseDto {

    Integer allocationId;
    Date date;
    String status;
    PatientDto patientDto;
    RoomDetailsDto room;
}
