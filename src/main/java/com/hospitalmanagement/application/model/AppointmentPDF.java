package com.hospitalmanagement.application.model;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "appointments")

@Getter
@Setter
public class AppointmentPDF {

    @Id
    private String id;
    private byte[] PDFDocument;
}
