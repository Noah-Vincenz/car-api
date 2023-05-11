package com.car.api;

import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.car.api.model.Car;
import com.car.api.repository.CarRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class CarApiIntegrationTest {

    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository repository;

    @Test
    public void test_getAllCars_emptyCars() {
        ResponseEntity<List<Car>> apiResponse = restTemplate.exchange("http://localhost:" + port + "/api/v1/cars",
                                                                      HttpMethod.GET,
                                                                      null,
                                                                      new ParameterizedTypeReference<>() {}
        );
        Assertions.assertNotNull(apiResponse.getBody());
        Assertions.assertTrue(apiResponse.getBody().isEmpty());
    }

    @Test
    public void test_getAllCars() {
        Car carToBeSaved = new Car("1", "soft top", ColourEnum.GREEN.toString());
        repository.save(carToBeSaved);
        ResponseEntity<List<Car>> apiResponse = restTemplate.exchange("http://localhost:" + port + "/api/v1/cars",
                                                                      HttpMethod.GET,
                                                                      null,
                                                                      new ParameterizedTypeReference<>() {}
        );
        Assertions.assertNotNull(apiResponse.getBody());
        Assertions.assertEquals(1, apiResponse.getBody().size());
        Car carRetrieved = apiResponse.getBody().get(0);
        Assertions.assertEquals(carToBeSaved, carRetrieved);
    }
}
