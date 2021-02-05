package com.ysuratask.services;

import com.ysuratask.entities.ParkingVehicleLocation;
import com.ysuratask.exceptions.FileException;
import com.ysuratask.models.VehicleParkingLocation;
import com.ysuratask.utils.AppUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.text.ParseException;

/**
 * Created by NrapendraKumar on 25-03-2016.
 */
@Component
@Qualifier(AppUtil.VEHICLE_PARKING_LOCATION_CONVERTER)
public class VehicleParkingLocationConverterServiceImpl implements ConverterService<VehicleParkingLocation,ParkingVehicleLocation> {

    @Override
    public VehicleParkingLocation convert(ParkingVehicleLocation parkingVehicleLocation) throws ParseException, FileException {
        VehicleParkingLocation vehicleParkingLocation = new VehicleParkingLocation();
        vehicleParkingLocation.setLevelNumber(parkingVehicleLocation.getLevelNumber());
        vehicleParkingLocation.setParkingLotNumber(parkingVehicleLocation.getVehicleLocationLotNumber());
        return vehicleParkingLocation;
    }
}
