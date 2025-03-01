package com.hospitalmanagement.application;


import com.hospitalmanagement.application.dto.AppointmentDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.http.HttpRequest;
import java.sql.Time;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppointmentTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @LocalServerPort
    int port;


    /*
    @Test
    void createAppointment(){
        String URI = "http://localhost:" + port + "/api/appointment/new";
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setUserId(1);
        appointmentDto.setDoctor(3);
        appointmentDto.setTime(new Time(System.currentTimeMillis()));
        appointmentDto.setDisease("Diabetes");
        HttpEntity<AppointmentDto> httpEntity = new HttpEntity<>(appointmentDto);
        ResponseEntity<Object> response =
                testRestTemplate
                        .exchange(
                                URI,
                                HttpMethod.POST,
                                httpEntity,
                                new ParameterizedTypeReference<Object>() {
                                }
                        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

     */
    @Test
    void duplicateAppointment(){

    }
}
