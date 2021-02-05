package com.ysuratask.controllers;

import com.ysuratask.exceptions.NotFoundException;
import com.ysuratask.models.FreeParkingGarageSpace;
import com.ysuratask.models.Vehicle;
import com.ysuratask.models.VehicleMovementStatus;
import com.ysuratask.models.VehicleParkingLocation;
import com.ysuratask.services.GarageService;
import com.ysuratask.utils.AppUtil;
import com.ysuratask.utils.AuthenticationUtil;
import com.ysuratask.utils.UrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by NrapendraKumar on 20-03-2016.
 */

@Controller
public class GarageController {

    private Logger logger = Logger.getLogger(GarageController.class.getName());

    @Autowired
    private GarageService garageService;

    @RequestMapping(value = UrlUtil.GARAGE_ENTER_VEHICLE_URL, method = RequestMethod.POST)
    public ResponseEntity<VehicleMovementStatus> enterVehicle(@RequestBody Vehicle vehicle) throws IOException, ParseException {
        VehicleMovementStatus vehicleMovementStatus = garageService.addVehicle(vehicle);
        return new ResponseEntity<VehicleMovementStatus>(vehicleMovementStatus, HttpStatus.OK);
    }

    @RequestMapping(value = UrlUtil.GARAGE_EXIT_VEHICLE_URL, method = RequestMethod.DELETE)
    public ResponseEntity<VehicleMovementStatus> exitVehicle(@NotNull @PathVariable(AppUtil.VEHICLE_NUMBER) String vehicleNumber) throws IOException, ParseException {
        VehicleMovementStatus vehicleMovementStatus = garageService.removeVehicle(vehicleNumber);
        return new ResponseEntity<VehicleMovementStatus>(vehicleMovementStatus, HttpStatus.OK);
    }

    @RequestMapping(value = UrlUtil.GARAGE_GET_VEHICLE_LOCATION_URL, method = RequestMethod.GET)
    public ResponseEntity<VehicleParkingLocation> getVehicleLocation(@NotNull @PathVariable(AppUtil.VEHICLE_NUMBER) String vehicleNumber) throws IOException, ParseException {
        VehicleParkingLocation vehicleParkingLocation = garageService.getVehicleParkingLocation(vehicleNumber);
        return new ResponseEntity<VehicleParkingLocation>(vehicleParkingLocation, HttpStatus.OK);
    }

    @RequestMapping(value = UrlUtil.GARAGE_FREE_SPACE_URL, method = RequestMethod.GET)
    public ResponseEntity<FreeParkingGarageSpace> getAllFreeSpaces() throws IOException, ParseException {
        FreeParkingGarageSpace freeParkingGarageSpace = garageService.getFreeParkingLotsInformation();
        return new ResponseEntity<FreeParkingGarageSpace>(freeParkingGarageSpace, HttpStatus.OK);
    }
}
