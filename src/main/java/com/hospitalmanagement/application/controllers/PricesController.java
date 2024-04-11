package com.hospitalmanagement.application.controllers;

import com.hospitalmanagement.application.models.Prices;
import com.hospitalmanagement.application.repositories.PricesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/prices")
public class PricesController {
    @Autowired
    PricesRepository pricesRepository;
    @PostMapping("/")
    Prices addNewPrice(@RequestBody Prices prices){
        return pricesRepository.save(prices);
    }
    @GetMapping("/{name}")
    public int getPrice(@PathVariable String name){
        return pricesRepository.findByDisease(name).getPrice();
    }
}
