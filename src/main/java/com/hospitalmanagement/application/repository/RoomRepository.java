package com.hospitalmanagement.application.repository;

import com.hospitalmanagement.application.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room,Integer> {
}
