package com.ysuratask.controllers;

import com.ysuratask.GarageApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

 @ExtendWith(SpringExtension.class)
 @SpringBootTest(classes = GarageApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GarageControllerTest {

     @Autowired
     private TestRestTemplate restTemplate;

     @Autowired
     private ParkingVehicleLocationRepository tutorialRepository;

     @Autowired
     private VehicleRepository tutorialRepository;


    @Test
    void enterVehicle() {
    }

    @Test
    void exitVehicle() {
    }

    @Test
    void getVehicleLocation() {
    }

    @Test
    void getAllFreeSpaces() {
    }
}