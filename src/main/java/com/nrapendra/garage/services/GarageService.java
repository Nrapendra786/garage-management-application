package com.nrapendra.garage.services;

import com.nrapendra.garage.exceptions.FileException;
import com.nrapendra.garage.models.FreeParkingGarageSpace;
import com.nrapendra.garage.models.Vehicle;
import com.nrapendra.garage.models.VehicleMovementStatus;
import com.nrapendra.garage.models.VehicleParkingLocation;
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
