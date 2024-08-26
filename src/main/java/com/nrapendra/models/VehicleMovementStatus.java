package com.nrapendra.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Created by NrapendraKumar
 */
@Component
public record VehicleMovementStatus(){
    private static String status;

    public static String getStatus() {
        return status;
    }

    public static void setStatus(String status) {
        VehicleMovementStatus.status = status;
    }




}
