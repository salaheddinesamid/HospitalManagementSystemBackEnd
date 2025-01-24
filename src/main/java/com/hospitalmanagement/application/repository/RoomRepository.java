package com.hospitalmanagement.application.repository;

import com.hospitalmanagement.application.model.Patient;
import com.hospitalmanagement.application.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room,Integer> {

    List<Room> findAllByAvailableFromBefore(Date date);
    Room findByPatient(Patient patient);
    Room findByRoomNumber(Integer roomNumber);
}
