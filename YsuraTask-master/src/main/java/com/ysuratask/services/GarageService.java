package com.ysuratask.services;

import com.ysuratask.entities.ParkingVehicleLocation;
import com.ysuratask.exceptions.FileException;
import com.ysuratask.models.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Created by NrapendraKumar on 20-03-2016.
 */
@Service
public interface GarageService {

    public VehicleMovementStatus addVehicle(Vehicle vehicle) throws IOException, ParseException;

    public VehicleMovementStatus removeVehicle(String vehicleId) throws IOException, ParseException;

    public VehicleParkingLocation getVehicleParkingLocation(String vehicleId) throws ParseException, FileException;

    public FreeParkingGarageSpace getFreeParkingLotsInformation() throws FileException, ParseException;
}
