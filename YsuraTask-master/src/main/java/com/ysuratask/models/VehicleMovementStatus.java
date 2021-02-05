package com.ysuratask.models;

import org.springframework.stereotype.Component;

/**
 * Created by NrapendraKumar on 20-03-2016.
 */

@Component
public class VehicleMovementStatus {

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
