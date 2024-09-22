package com.nrapendra.garage.models;

import com.nrapendra.garage.enums.VehicleType;
import lombok.Builder;

/**
 * Created by NrapendraKumar
 */

@Builder
public record Vehicle( VehicleType vehicleType, String vehicleNumber) {

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public VehicleType vehicleType() {
        return vehicleType;
    }

}