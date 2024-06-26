package com.ysuratask.models;

import com.ysuratask.enums.VehicleType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by NrapendraKumar on 20-03-2016.
 */


@Getter
@Setter
public class Vehicle  {

    private VehicleType vehicleType;

    private String vehicleNumber;
}
