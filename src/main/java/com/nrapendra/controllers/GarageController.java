package com.nrapendra.controllers;

import com.nrapendra.models.FreeParkingGarageSpace;
import com.nrapendra.models.Vehicle;
import com.nrapendra.models.VehicleMovementStatus;
import com.nrapendra.models.VehicleParkingLocation;
import com.nrapendra.services.GarageService;
import com.nrapendra.utils.AppUtil;
import com.nrapendra.utils.UrlUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by NrapendraKumar
 */

@RestController
@Slf4j
public class GarageController {

    @Autowired
    private GarageService garageService;

    @RequestMapping(value = UrlUtil.GARAGE_ENTER_VEHICLE_URL, method = RequestMethod.POST)
    public ResponseEntity<VehicleMovementStatus> enterVehicle(@RequestBody Vehicle vehicle) throws IOException, ParseException {
        VehicleMovementStatus vehicleMovementStatus = garageService.addVehicle(vehicle);
        return new ResponseEntity<VehicleMovementStatus>(vehicleMovementStatus, HttpStatus.OK);
    }

    @RequestMapping(value = UrlUtil.GARAGE_EXIT_VEHICLE_URL, method = RequestMethod.DELETE)
    public ResponseEntity<VehicleMovementStatus> exitVehicle(@PathVariable(AppUtil.VEHICLE_NUMBER) String vehicleNumber) throws IOException, ParseException {
        VehicleMovementStatus vehicleMovementStatus = garageService.removeVehicle(vehicleNumber);
        return new ResponseEntity<VehicleMovementStatus>(vehicleMovementStatus, HttpStatus.OK);
    }

    @RequestMapping(value = UrlUtil.GARAGE_GET_VEHICLE_LOCATION_URL, method = RequestMethod.GET)
    public ResponseEntity<VehicleParkingLocation> getVehicleLocation(@PathVariable(AppUtil.VEHICLE_NUMBER) String vehicleNumber) throws IOException, ParseException {
        VehicleParkingLocation vehicleParkingLocation = garageService.getVehicleParkingLocation(vehicleNumber);
        return new ResponseEntity<VehicleParkingLocation>(vehicleParkingLocation, HttpStatus.OK);
    }

    @RequestMapping(value = UrlUtil.GARAGE_FREE_SPACE_URL, method = RequestMethod.GET)
    public ResponseEntity<FreeParkingGarageSpace> getAllFreeSpaces() throws IOException, ParseException {
        FreeParkingGarageSpace freeParkingGarageSpace = garageService.getFreeParkingLotsInformation();
        return new ResponseEntity<FreeParkingGarageSpace>(freeParkingGarageSpace, HttpStatus.OK);
    }
}
