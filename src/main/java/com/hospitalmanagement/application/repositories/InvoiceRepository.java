package com.hospitalmanagement.application.repositories;

import com.hospitalmanagement.application.models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice,Long> {

}
