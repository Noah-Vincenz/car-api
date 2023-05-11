package com.car.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.car.api.model.Car;
import com.car.api.model.ErrorResponse;
import com.car.api.service.CarService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/")
@RequiredArgsConstructor
public class CarController {

    private final CarService service;

    /**
     * TODO: Add colour enum and in getAllCars if we catch a BAD_REQUEST then we can return a message: "Colour must be one of [BLUE, RED, YELLOW]"
     */
    /**
     * Get all cars from DB.
     */
    @GetMapping("cars")
    public ResponseEntity<List<Car>> getAllCars(@RequestParam(name = "colour", required = false) String colour) {
        try {
            List<Car> returnedCars = service.getAllCars(colour);
            return new ResponseEntity(returnedCars, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to retrieve all cars"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get car by given ID from DB.
     */
    @GetMapping("cars/{id}")
    public ResponseEntity<Car> getCar(@PathVariable String id) {
        try {
            Optional<Car> returnedCarOptional = service.getCar(id);
            return returnedCarOptional.map(car -> new ResponseEntity(car, HttpStatus.OK))
                                      .orElseGet(() -> new ResponseEntity(new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Failed to retrieve car with given ID"), HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to retrieve car with given ID"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Create car in our DB
     */
    @PostMapping("cars")
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        try {
            service.createCar(car);
            return new ResponseEntity(null, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to create car with given details"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Create car in our DB
     */
    @DeleteMapping("cars/{id}")
    public ResponseEntity<Car> deleteCar(@PathVariable String id) {
        try {
            service.deleteCar(id);
            return new ResponseEntity(null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to delete car with given ID"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
