package com.nrapendra.models;

import com.nrapendra.enums.VehicleType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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