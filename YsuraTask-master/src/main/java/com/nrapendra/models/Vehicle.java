package com.nrapendra.models;

import com.nrapendra.enums.VehicleType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by NrapendraKumar
 */


@Getter
@Setter
@Builder
public class Vehicle  {

    private VehicleType vehicleType;

    private String vehicleNumber;
}
