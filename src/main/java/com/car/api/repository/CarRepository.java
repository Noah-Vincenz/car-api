package com.car.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.car.api.model.Car;

public interface CarRepository extends JpaRepository<Car, String> {
    public List<Car> findCarByColour(String colour);
}
