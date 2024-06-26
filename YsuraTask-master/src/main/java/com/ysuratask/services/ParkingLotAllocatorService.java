package com.ysuratask.services;

import com.ysuratask.entities.ParkingVehicleLocation;
import com.ysuratask.entities.VehicleInformation;
import com.ysuratask.exceptions.FileException;
import com.ysuratask.models.GarageSpaceInformation;
import com.ysuratask.repositories.ParkingVehicleLocationRespository;
import com.ysuratask.utils.AppUtil;
import com.ysuratask.utils.NumberUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to allocate ParkingLot
 * Created by NrapendraKumar on 21-03-2016.
 */

@Component
@Slf4j
@AllArgsConstructor
public class ParkingLotAllocatorService {

    //@Autowired
    private FileReaderService garageSpaceInfoFileReaderService;

    @Autowired
    private ParkingVehicleLocationRespository parkingVehicleLocationRespository;

    public ParkingVehicleLocation allocateParkingLot() throws FileException, ParseException {
        List<VehicleInformation> vehicleInformationList = new ArrayList<>();
        ParkingVehicleLocation parkingVehicleLocation = null;
        List<GarageSpaceInformation> garageSpaceInformationList = garageSpaceInfoFileReaderService.read();
        for(GarageSpaceInformation garageSpaceInformation : garageSpaceInformationList) {
            int leveleNo = garageSpaceInformation.getNoOfLevels();
            int filledPositionByVehicle = getFilledPositionByVehicle(leveleNo);
            int spaceForNoOfVehicle = garageSpaceInformation.getParkingLotPerLevel();
            if(isFreeSpaceAvailableInGarage(spaceForNoOfVehicle,filledPositionByVehicle,leveleNo)) {
                int nextParkingLocation = filledPositionByVehicle + NumberUtil.ONE;
                parkingVehicleLocation = new ParkingVehicleLocation();
                parkingVehicleLocation.setLevelNumber(leveleNo);
                parkingVehicleLocation.setVehicleLocationLotNumber(nextParkingLocation);
                break;
            }
        }
        return parkingVehicleLocation;
    }

    private int getFilledPositionByVehicle(int levelNo){
        return (int)parkingVehicleLocationRespository.findAll().
                              stream().filter((ParkingVehicleLocation p) -> p.getLevelNumber() == levelNo).count();
    }

    private boolean isFreeSpaceAvailableInGarage(int spaceForNoOfVehicle,int filledPositionByVehicle,int leveleNo) {
        return spaceForNoOfVehicle - filledPositionByVehicle > NumberUtil.ZERO && leveleNo <= AppUtil.LEVEL_LIMIT;
    }
}














