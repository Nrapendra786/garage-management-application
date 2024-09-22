package com.nrapendra.garage.models;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by NrapendraKumar
 */
public record FreeParkingGarageSpace() {

    static int noOfFreeParkingLot;
    static String parkingSpaceStatus;

    public int getNoOfFreeParkingLot(){
        return noOfFreeParkingLot;
    }

    public String getParkingSpaceStatus(){
        return parkingSpaceStatus;
    }

    public void setNoOfFreeParkingLot(int _freeSpaceInGarage) {
        noOfFreeParkingLot = _freeSpaceInGarage;
    }

    public void setParkingSpaceStatus(String _parkingSpaceStatus) {
        parkingSpaceStatus = _parkingSpaceStatus;
    }
}


