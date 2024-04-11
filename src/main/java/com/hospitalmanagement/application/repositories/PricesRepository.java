package com.hospitalmanagement.application.repositories;

import com.hospitalmanagement.application.models.Prices;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PricesRepository extends JpaRepository<Prices,Long> {
    Prices findByDisease(String disease);
}
