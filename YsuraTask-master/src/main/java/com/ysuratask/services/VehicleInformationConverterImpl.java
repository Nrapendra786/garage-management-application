package com.ysuratask.services;

import com.ysuratask.entities.ParkingVehicleLocation;
import com.ysuratask.entities.VehicleInformation;
import com.ysuratask.exceptions.FileException;
import com.ysuratask.models.Vehicle;
import com.ysuratask.utils.AppUtil;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class VehicleInformationConverterImpl implements ConverterService<VehicleInformation,Vehicle> {

    private ParkingLotAllocatorService parkingLotAllocator;

    private VehicleInformation vehicleInformation;

    public VehicleInformation convert(Vehicle vehicle) throws ParseException, FileException {
        ParkingVehicleLocation parkingVehicleLocation = parkingLotAllocator.allocateParkingLot();
        VehicleInformation vehicleInformation=null;
        if(parkingVehicleLocation != null) {
            vehicleInformation = new VehicleInformation();
            vehicleInformation.setVehicleType(vehicle.getVehicleType());
            vehicleInformation.setVehicleEnterDate(DateTime.now().toDate());
            vehicleInformation.setVehicleParkingLocation(parkingVehicleLocation);
            vehicleInformation.setVehicleNumber(vehicle.getVehicleNumber());
        }
        return vehicleInformation;
    }
}
