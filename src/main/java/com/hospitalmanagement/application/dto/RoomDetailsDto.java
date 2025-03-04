package com.hospitalmanagement.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDetailsDto {

    private Integer roomId;
    private Integer roomNumber;
    private Integer capacity;
    private Date availableFrom;
    private String status;
    private Integer pricePerDay;
    private PatientDto patientDto;

    public RoomDetailsDto(Integer id, Integer roomNumber, String status, Integer capacity, PatientDto patientDto) {
        this.roomId = id;
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.status = status;
        this.patientDto = patientDto;
    }
}
