package com.hospitalmanagement.application.departments.staff_management;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff,Integer> {
    List<Staff> findAllByRole(String role);
}
