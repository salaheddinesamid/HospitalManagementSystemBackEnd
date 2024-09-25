package com.hospitalmanagement.application.repository;

import com.hospitalmanagement.application.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact,Integer> {
}