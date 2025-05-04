package com.nrapendra.controllers;

import com.nrapendra.GarageApplication;
import com.nrapendra.garage.entities.ParkingVehicleLocation;
import com.nrapendra.garage.entities.VehicleInformation;
import com.nrapendra.garage.enums.VehicleType;
import com.nrapendra.garage.models.FreeParkingGarageSpace;
import com.nrapendra.garage.models.Vehicle;
import com.nrapendra.garage.models.VehicleMovementStatus;
import com.nrapendra.garage.models.VehicleParkingLocation;
import com.nrapendra.garage.repositories.ParkingVehicleLocationRepository;
import com.nrapendra.garage.repositories.VehicleRepository;
import com.nrapendra.garage.services.ConverterService;
import com.nrapendra.garage.services.GarageService;
import com.nrapendra.garage.utils.AppUtil;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
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
    private GarageService garageService;

    @Autowired
    private ParkingVehicleLocationRepository parkingVehicleLocationRespository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    @Qualifier(AppUtil.VEHICLE_INFORMATION_CONVERTER)
    private ConverterService<VehicleInformation, Vehicle> vehicleInformationConverterService;

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
                testRestTemplate.postForEntity(getRootUrl() + GARAGE_ENTER_VEHICLE_URL, vehicle, VehicleMovementStatus.class);

        //then
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        assertEquals(VEHICLE_ENTERED, postResponse.getBody().getStatus());
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());
    }

    @Test
    void vehicleAlreadyExist() {
        //given
        Vehicle vehicle = vehicle();

        //when
        testRestTemplate.postForEntity(getRootUrl() + GARAGE_ENTER_VEHICLE_URL, vehicle, VehicleMovementStatus.class);
        ResponseEntity<VehicleMovementStatus> postResponse =
                testRestTemplate.postForEntity(getRootUrl() + GARAGE_ENTER_VEHICLE_URL, vehicle, VehicleMovementStatus.class);

        //then
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        assertEquals(VEHICLE_ALREADY_EXIST, postResponse.getBody().getStatus());
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());
    }

    @Test
    void exitVehicle() {
        //given
        Vehicle vehicle = vehicle();

        //when
        testRestTemplate.postForEntity(getRootUrl() + GARAGE_ENTER_VEHICLE_URL, vehicle, VehicleMovementStatus.class);
        ResponseEntity<VehicleMovementStatus> deleteResponse = testRestTemplate.
                exchange(URI.create(getRootUrl() + GARAGE_EXIT_VEHICLE_URL + vehicle.getVehicleNumber()),
                        HttpMethod.DELETE,
                        HttpEntity.EMPTY,
                        VehicleMovementStatus.class);

        //then
        assertNotNull(deleteResponse);
        assertNotNull(deleteResponse.getBody());
        assertEquals(VEHICLE_EXITED, deleteResponse.getBody().getStatus());
        assertEquals(HttpStatus.OK, deleteResponse.getStatusCode());
    }

    @Test
    void vehicleDoesNotExist() {
        //given
        Vehicle vehicle = vehicle();

        //when
        ResponseEntity<VehicleMovementStatus> deleteResponse = testRestTemplate.
                exchange(URI.create(getRootUrl() + GARAGE_EXIT_VEHICLE_URL + vehicle.getVehicleNumber()),
                        HttpMethod.DELETE,
                        HttpEntity.EMPTY,
                        VehicleMovementStatus.class);

        //then
        assertNotNull(deleteResponse);
        assertNotNull(deleteResponse.getBody());
        assertEquals(VEHICLE_DOES_NOT_EXIST, deleteResponse.getBody().getStatus());
        assertEquals(HttpStatus.NOT_FOUND, deleteResponse.getStatusCode());
    }

    @Test
    void getVehicleLocationWithCorrectUserNameAndPassword() {

        //given
        Vehicle vehicle = vehicle();
        saveToVehicle();

        //when
        ResponseEntity<VehicleParkingLocation> getResponse =
                testRestTemplate
                        .withBasicAuth(TestUtil.CORRECT_USER, TestUtil.CORRECT_PASSWORD)
                        .getForEntity(URI.create(getRootUrl() + GARAGE_GET_VEHICLE_LOCATION_URL + vehicle.getVehicleNumber()), VehicleParkingLocation.class);

        //then
        assertNotNull(getResponse);
        assertNotNull(getResponse.getBody());
        assertEquals(getResponse.getStatusCode(), HttpStatusCode.valueOf(200));
        assertEquals(4, Objects.requireNonNull(getResponse.getBody()).getLevelNumber());
        assertEquals(2, Objects.requireNonNull(getResponse.getBody()).getParkingLotNumber());
    }

    @Test
    void getVehicleLocationWithWrongUserAndWrongPassword() {
        //given
        Vehicle vehicle = vehicle();
        saveToVehicle();

        //when
        ResponseEntity<VehicleParkingLocation> getResponse =
                testRestTemplate
                        .withBasicAuth(TestUtil.INCORRECT_USER, TestUtil.INCORRECT_PASSWORD)
                        .getForEntity(URI.create(getRootUrl() + GARAGE_GET_VEHICLE_LOCATION_URL + vehicle.getVehicleNumber()), VehicleParkingLocation.class);
        //then
        assertNotNull(getResponse);
        assertEquals(HttpStatus.UNAUTHORIZED, getResponse.getStatusCode());
    }

    @Test
    void getAllFreeSpacesWithCorrectUsernameAndCorrectPassword() {
        //when
        ResponseEntity<FreeParkingGarageSpace> getResponse =
                testRestTemplate
                        .withBasicAuth(TestUtil.CORRECT_USER, TestUtil.CORRECT_PASSWORD)
                        .getForEntity(URI.create(getRootUrl() + GARAGE_FREE_SPACE_URL ),FreeParkingGarageSpace.class);

        //then
        assertNotNull(getResponse);
        assertEquals(getResponse.getStatusCode(), HttpStatusCode.valueOf(200));
        assertNotNull(getResponse.getBody());
        assertEquals(10, getResponse.getBody().getNoOfFreeParkingLot());
        assertEquals(PARKING_SPACE_AVAILABLE, getResponse.getBody().getParkingSpaceStatus());
    }

    @Test
    void getAllFreeSpacesWithInCorrectUsernameAndInCorrectPassword() {
        //when
        ResponseEntity<FreeParkingGarageSpace> getResponse =
                testRestTemplate
                        .withBasicAuth(TestUtil.INCORRECT_USER, TestUtil.INCORRECT_PASSWORD)
                        .getForEntity(URI.create(getRootUrl() + GARAGE_FREE_SPACE_URL ),FreeParkingGarageSpace.class);
        //then
        assertNotNull(getResponse);
        assertEquals(HttpStatus.UNAUTHORIZED, getResponse.getStatusCode());
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