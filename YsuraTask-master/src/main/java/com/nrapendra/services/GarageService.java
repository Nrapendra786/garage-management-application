package com.nrapendra.services;

import com.nrapendra.exceptions.FileException;
import com.nrapendra.models.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by NrapendraKumar
 */
@Service
public interface GarageService {

     VehicleMovementStatus addVehicle(Vehicle vehicle) throws IOException, ParseException;

     VehicleMovementStatus removeVehicle(String vehicleId) throws IOException, ParseException;

     VehicleParkingLocation getVehicleParkingLocation(String vehicleId) throws ParseException, FileException;

     FreeParkingGarageSpace getFreeParkingLotsInformation() throws FileException, ParseException;
}
