package com.car.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.car.api.model.Car;

public interface CarRepository extends JpaRepository<Car, String> {}
