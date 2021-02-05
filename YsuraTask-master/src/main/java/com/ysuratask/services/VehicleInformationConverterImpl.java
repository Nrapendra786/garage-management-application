package com.ysuratask.services;

import com.ysuratask.entities.ParkingVehicleLocation;
import com.ysuratask.entities.VehicleInformation;
import com.ysuratask.exceptions.FileException;
import com.ysuratask.helpers.ParkingLotAllocator;
import com.ysuratask.models.Vehicle;
import com.ysuratask.utils.AppUtil;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.text.ParseException;

/**
 * Created by NrapendraKumar on 23-03-2016.
 */

@Component
@Qualifier(AppUtil.VEHICLE_INFORMATION_CONVERTER)
public class VehicleInformationConverterImpl implements ConverterService<VehicleInformation,Vehicle> {

    @Autowired
    private ParkingLotAllocator parkingLotAllocator;

    private VehicleInformation vehicleInformation;

    public VehicleInformation convert(Vehicle vehicle) throws ParseException, FileException {
        ParkingVehicleLocation parkingVehicleLocation = parkingLotAllocator.allocateParkingLot();
        if(parkingVehicleLocation != null) {
            vehicleInformation = new VehicleInformation();
            vehicleInformation.setVehicleType(vehicle.getVehicleType());
            vehicleInformation.setVehicleEnterDate(DateTime.now().toDate());
            vehicleInformation.setParkingVehicleLocation(parkingVehicleLocation);
            vehicleInformation.setVehicleNumber(vehicle.getVehicleNumber());
        }
        return vehicleInformation;
    }
}
