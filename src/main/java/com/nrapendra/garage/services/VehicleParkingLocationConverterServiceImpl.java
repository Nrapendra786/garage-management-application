package com.nrapendra.garage.services;

import com.nrapendra.garage.entities.ParkingVehicleLocation;
import com.nrapendra.garage.exceptions.FileException;
import com.nrapendra.garage.models.VehicleParkingLocation;
import com.nrapendra.garage.utils.AppUtil;
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
