package com.nrapendra.garage.models;

import lombok.*;

/**
 * Created by NrapendraKumar
 */
@Builder
public record VehicleParkingLocation( int levelNumber, int parkingLotNumber){

    public int getLevelNumber() {
        return levelNumber;
    }

    public int getParkingLotNumber() {
        return parkingLotNumber;
    }
}
