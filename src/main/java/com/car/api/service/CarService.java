package com.car.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.car.api.model.Car;
import com.car.api.repository.CarRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository repository;

    public void createCar(Car car) {
        repository.save(new Car(car.getId(), car.getType(), car.getColour()));
    }

    public List<Car> getAllCars(String colour) {
        if (colour == null) {
            return repository.findAll();
        } else {
            return repository.findCarByColour(colour);
        }
    }

    public Optional<Car> getCar(String id) {
        return repository.findById(id);
    }

    public void deleteCar(String id) {
        repository.deleteById(id);
    }
}
