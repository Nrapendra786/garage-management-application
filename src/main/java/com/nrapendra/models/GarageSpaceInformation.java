package com.nrapendra.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by NrapendraKumar
 */
@Builder
public record GarageSpaceInformation(int parkingLotPerLevel,int noOfLevels){

    public int getParkingLotPerLevel() {
        return parkingLotPerLevel;
    }

    public Integer getNoOfLevels() {
        return noOfLevels;
    }
}