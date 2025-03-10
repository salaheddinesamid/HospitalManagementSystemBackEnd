package com.hospitalmanagement.application.service;

import com.hospitalmanagement.application.model.Appointment;
import com.hospitalmanagement.application.model.AppointmentPDF;
import com.hospitalmanagement.application.repository.AppointmentPdfRepository;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;

/*
@Service
public class PdfGenerator {

    private final AppointmentPdfRepository appointmentPdfRepository;

    @Autowired
    public PdfGenerator(AppointmentPdfRepository appointmentPdfRepository) {
        this.appointmentPdfRepository = appointmentPdfRepository;
    }

    public byte[] generateAppointmentDocument(Appointment appointment) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        document.add(new Paragraph("Appointment Details"));
        document.add(new Paragraph("Patient Name: " + appointment.getPatient().getFirstName() + " " + appointment.getPatient().getLastName()));
        document.add(new Paragraph("National Id: " + appointment.getPatient().getNationalId()));
        document.add(new Paragraph("Date: " + appointment.getDate()));

        document.close();  // Close document to finalize PDF

        // Store PDF as byte array in MongoDB
        AppointmentPDF appointmentPDF = new AppointmentPDF();
        appointmentPDF.setPDFDocument(outputStream.toByteArray());

        appointmentPdfRepository.save(appointmentPDF);

        return outputStream.toByteArray();

    }
}


 */