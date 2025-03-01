package com.hospitalmanagement.application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RequestTest {
    @Autowired
    TestRestTemplate testRestTemplate;

    @LocalServerPort
    int port;

    /*
    @Test
    void shouldReturnOK(){

        String URI = "http://localhost:" + port + "admin/test";
        ResponseEntity<String> response = testRestTemplate.getForEntity(
                URI,
                String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

     */
}
