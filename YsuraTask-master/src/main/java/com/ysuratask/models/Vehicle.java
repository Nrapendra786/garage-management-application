package com.ysuratask.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ysuratask.enums.VehicleType;
import com.ysuratask.utils.AppUtil;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by NrapendraKumar on 20-03-2016.
 */
public class Vehicle implements Serializable{

    @NotNull
    private VehicleType vehicleType;

    @NotNull
    private String vehicleNumber;

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
}
