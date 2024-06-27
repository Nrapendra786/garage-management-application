package com.nrapendra.services;

import com.nrapendra.entities.ParkingVehicleLocation;
import com.nrapendra.exceptions.FileException;
import com.nrapendra.models.VehicleParkingLocation;
import com.nrapendra.utils.AppUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.text.ParseException;

/**
 * Created by NrapendraKumar
 */
@Component
@Qualifier(AppUtil.VEHICLE_PARKING_LOCATION_CONVERTER)
public class VehicleParkingLocationConverterServiceImpl implements ConverterService<VehicleParkingLocation,ParkingVehicleLocation> {

    @Override
    public VehicleParkingLocation convert(ParkingVehicleLocation parkingVehicleLocation) throws ParseException, FileException {

        return VehicleParkingLocation.builder()
                .levelNumber(parkingVehicleLocation.getLevelNumber())
                .parkingLotNumber(parkingVehicleLocation.getVehicleLocationLotNumber())
                .build();
    }
}
