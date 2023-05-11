package com.car.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.car.api.model.Car;
import com.car.api.repository.CarRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository repository;

    public List<Car> getAllCars() {
        return repository.findAll();
    }
}
