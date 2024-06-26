package com.nrapendra.controllers;

import com.nrapendra.GarageApplication;
import com.nrapendra.entities.ParkingVehicleLocation;
import com.nrapendra.entities.VehicleInformation;
import com.nrapendra.enums.VehicleType;
import com.nrapendra.models.FreeParkingGarageSpace;
import com.nrapendra.models.Vehicle;
import com.nrapendra.models.VehicleMovementStatus;
import com.nrapendra.models.VehicleParkingLocation;
import com.nrapendra.repositories.ParkingVehicleLocationRespository;
import com.nrapendra.repositories.VehicleRepository;
import com.nrapendra.services.ConverterService;
import com.nrapendra.services.GarageService;
import com.nrapendra.utils.AppUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URI;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

import static com.nrapendra.controllers.TestUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = GarageApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GarageControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private GarageService garageService;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ParkingVehicleLocationRespository parkingVehicleLocationRespository;

    @Autowired
    @Qualifier(AppUtil.VEHICLE_INFORMATION_CONVERTER)
    private ConverterService vehicleInformationConverterService;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @BeforeEach
    public void before() {
        vehicleRepository.deleteAll();
        parkingVehicleLocationRespository.deleteAll();
    }

    @Test
    void enterVehicle() {
        //given
        Vehicle vehicle = vehicle();

        //when
        ResponseEntity<VehicleMovementStatus> postResponse =
                restTemplate.postForEntity(getRootUrl() + GARAGE_ENTER_VEHICLE_URL, vehicle, VehicleMovementStatus.class);

        //then
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        assertEquals(postResponse.getBody().getStatus(), VEHICLE_ENTERED);
        assertEquals(postResponse.getStatusCode(),HttpStatusCode.valueOf(200));
    }

    @Test
    void vehicleAlreadyExist() {
        //given
        Vehicle vehicle = vehicle();

        //when
        restTemplate.postForEntity(getRootUrl() + GARAGE_ENTER_VEHICLE_URL, vehicle, VehicleMovementStatus.class);
        ResponseEntity<VehicleMovementStatus> postResponse =
                restTemplate.postForEntity(getRootUrl() + GARAGE_ENTER_VEHICLE_URL, vehicle, VehicleMovementStatus.class);

        //then
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        assertEquals(postResponse.getBody().getStatus(), VEHICLE_ALREADY_EXIST);
        assertEquals(postResponse.getStatusCode(),HttpStatusCode.valueOf(200));
    }


    @Test
    void exitVehicle() {
        //given
        Vehicle vehicle = vehicle();

        //when
        restTemplate.postForEntity(getRootUrl() + GARAGE_ENTER_VEHICLE_URL, vehicle, VehicleMovementStatus.class);
        ResponseEntity<VehicleMovementStatus> deleteResponse = restTemplate.
                exchange(URI.create(getRootUrl() + GARAGE_EXIT_VEHICLE_URL + vehicle.getVehicleNumber()),
                        HttpMethod.DELETE,
                        HttpEntity.EMPTY,
                        VehicleMovementStatus.class);

        //then
        assertNotNull(deleteResponse);
        assertNotNull(deleteResponse.getBody());
        assertEquals(deleteResponse.getBody().getStatus(),VEHICLE_EXITED);
        assertEquals(deleteResponse.getStatusCode(),HttpStatusCode.valueOf(200));
    }

    @Test
    void vehicleDoesNotExist() {
        //given
        Vehicle vehicle = vehicle();

        //when
        ResponseEntity<VehicleMovementStatus> deleteResponse = restTemplate.
                exchange(URI.create(getRootUrl() + GARAGE_EXIT_VEHICLE_URL + vehicle.getVehicleNumber()),
                        HttpMethod.DELETE,
                        HttpEntity.EMPTY,
                        VehicleMovementStatus.class);

        //then
        assertNotNull(deleteResponse);
        assertNotNull(deleteResponse.getBody());
        assertEquals(deleteResponse.getBody().getStatus(),VEHICLE_DOES_NOT_EXIST);
        assertEquals(deleteResponse.getStatusCode(),HttpStatusCode.valueOf(200));
    }

    @Test
    void getVehicleLocationWithCorrectUserNameAndPassword() {

        //given
        Vehicle vehicle = vehicle();
        saveToVehicle();

        //when
        ResponseEntity<VehicleParkingLocation> getResponse =
                restTemplate
                        .withBasicAuth(TestUtil.CORRECT_USER, TestUtil.CORRECT_PASSWORD)
                        .getForEntity(URI.create(getRootUrl() + GARAGE_GET_VEHICLE_LOCATION_URL + vehicle.getVehicleNumber()), VehicleParkingLocation.class);

        //then
        assertNotNull(getResponse);
        assertNotNull(getResponse.getBody());
        assertEquals(getResponse.getStatusCode(), HttpStatusCode.valueOf(200));
        assertEquals(Objects.requireNonNull(getResponse.getBody()).getLevelNumber(), 4);
        assertEquals(Objects.requireNonNull(getResponse.getBody()).getParkingLotNumber(), 2);
    }

    @Test
    void getVehicleLocationWithWrongUserAndWrongPassword() {
        //given
        Vehicle vehicle = vehicle();
        saveToVehicle();

        //when
        ResponseEntity<VehicleParkingLocation> getResponse =
                restTemplate
                        .withBasicAuth(TestUtil.INCORRECT_USER, TestUtil.INCORRECT_PASSWORD)
                        .getForEntity(URI.create(getRootUrl() + GARAGE_GET_VEHICLE_LOCATION_URL + vehicle.getVehicleNumber()), VehicleParkingLocation.class);
        //then
        assertNotNull(getResponse);
        assertEquals(getResponse.getStatusCode(), HttpStatusCode.valueOf(401));
    }

    @Test
    void getAllFreeSpacesWithCorrectUsernameAndCorrectPassword() {
        //when
        ResponseEntity<FreeParkingGarageSpace> getResponse =
                restTemplate
                        .withBasicAuth(TestUtil.CORRECT_USER, TestUtil.CORRECT_PASSWORD)
                        .getForEntity(URI.create(getRootUrl() + GARAGE_FREE_SPACE_URL ),FreeParkingGarageSpace.class);

        //then
        assertNotNull(getResponse);
        assertEquals(getResponse.getStatusCode(), HttpStatusCode.valueOf(200));
        assertNotNull(getResponse.getBody());
        assertEquals(getResponse.getBody().getNoOfFreeParkingLot(), 10);
        assertEquals(getResponse.getBody().getParkingSpaceStatus(), PARKING_SPACE_AVAILABLE);
    }

    @Test
    void getAllFreeSpacesWithInCorrectUsernameAndInCorrectPassword() {
        //when
        ResponseEntity<FreeParkingGarageSpace> getResponse =
                restTemplate
                        .withBasicAuth(TestUtil.INCORRECT_USER, TestUtil.INCORRECT_PASSWORD)
                        .getForEntity(URI.create(getRootUrl() + GARAGE_FREE_SPACE_URL ),FreeParkingGarageSpace.class);
        //then
        assertNotNull(getResponse);
        assertEquals(getResponse.getStatusCode(), HttpStatusCode.valueOf(401));
    }

    private Vehicle vehicle() {
        return Vehicle.builder().vehicleType(VehicleType.CAR).vehicleNumber("AD-7865").build();
    }

    private void saveToVehicle() {
        ParkingVehicleLocation parkingVehicleLocation = ParkingVehicleLocation
                .builder()
                .parkingVehicleLocationId(1)
                .vehicleLocationLotNumber(2)
                .levelNumber(4)
                .build();
        parkingVehicleLocationRespository.save(parkingVehicleLocation);

        VehicleInformation vehicleInformation = VehicleInformation
                .builder()
                .id(1L)
                .vehicleParkingLocation(parkingVehicleLocation)
                .vehicleEnterDate(Date.from(Instant.now()))
                .vehicleNumber(vehicle().getVehicleNumber())
                .vehicleType(VehicleType.CAR)
                .build();

        vehicleRepository.save(vehicleInformation);
    }
}