package com.nrapendra.garage.services;

import com.nrapendra.garage.entities.ParkingVehicleLocation;
import com.nrapendra.garage.entities.VehicleInformation;
import com.nrapendra.garage.exceptions.FileException;
import com.nrapendra.garage.models.Vehicle;
import com.nrapendra.garage.utils.AppUtil;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

/**
 * Created by NrapendraKumar
 */

@Component
@Qualifier(AppUtil.VEHICLE_INFORMATION_CONVERTER)
@AllArgsConstructor
public class VehicleInformationConverterImpl implements ConverterService<VehicleInformation, Vehicle> {

    private ParkingLotAllocatorService parkingLotAllocator;

    public VehicleInformation convert(@NonNull Vehicle vehicle) throws ParseException, FileException {
        ParkingVehicleLocation parkingVehicleLocation = parkingLotAllocator.allocateParkingLot();
        VehicleInformation vehicleInformation = null;
        if (Objects.nonNull(parkingVehicleLocation)) {
            vehicleInformation = VehicleInformation.builder()
                    .vehicleType(vehicle.vehicleType())
                    .vehicleEnterDate(Date.from(Instant.now()))
                    .vehicleParkingLocation(parkingVehicleLocation)
                    .vehicleNumber(vehicle.getVehicleNumber())
                    .build();
        }
        return vehicleInformation;
    }
}
