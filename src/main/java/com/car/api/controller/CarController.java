package com.car.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.car.api.model.Car;
import com.car.api.service.CarService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/")
@RequiredArgsConstructor
public class CarController {

    private final CarService service;

    /**
     * Get all cars from DB.
     */
    @GetMapping("cars")
    public ResponseEntity<List<Car>> getAllCars() {
        try {
            List<Car> returnedCars = service.getAllCars();
            return new ResponseEntity(returnedCars, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
