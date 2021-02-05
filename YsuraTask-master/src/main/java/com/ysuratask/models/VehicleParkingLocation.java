package com.ysuratask.models;

/**
 * Created by NrapendraKumar on 21-03-2016.
 */
public class VehicleParkingLocation {
    private int levelNumber;
    private int parkingLotNumber;

    public long getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public long getParkingLotNumber() {
        return parkingLotNumber;
    }

    public void setParkingLotNumber(int parkingLotNumber) {
        this.parkingLotNumber = parkingLotNumber;
    }
}
