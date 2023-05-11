package com.car.api.model;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ErrorResponse {
    private HttpStatus statusCode;
    private String message;
}
