package com.nrapendra.services;

import com.nrapendra.entities.ParkingVehicleLocation;
import com.nrapendra.exceptions.FileException;
import com.nrapendra.models.GarageSpaceInformation;
import com.nrapendra.repositories.ParkingVehicleLocationRespository;
import com.nrapendra.utils.AppUtil;
import com.nrapendra.utils.NumberUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.List;

/**
 * This class is used to allocate ParkingLot
 * Created by NrapendraKumar
 */

@Component
@Slf4j
@AllArgsConstructor
public class ParkingLotAllocatorService {

    private FileReaderService<GarageSpaceInformation> garageSpaceInfoFileReaderService;

    private ParkingVehicleLocationRespository parkingVehicleLocationRespository;

    public ParkingVehicleLocation allocateParkingLot() throws FileException, ParseException {
        ParkingVehicleLocation parkingVehicleLocation = null;
        List<GarageSpaceInformation> garageSpaceInformationList = garageSpaceInfoFileReaderService.read();
        for(GarageSpaceInformation garageSpaceInformation : garageSpaceInformationList) {
            int levelNumber = garageSpaceInformation.getNoOfLevels();
            int filledPositionByVehicle = getFilledPositionByVehicle(levelNumber);
            int spaceForNoOfVehicle = garageSpaceInformation.getParkingLotPerLevel();
            if(isFreeSpaceAvailableInGarage(spaceForNoOfVehicle,filledPositionByVehicle, levelNumber)) {
                int nextParkingLocation = filledPositionByVehicle + NumberUtil.ONE;
                parkingVehicleLocation =  ParkingVehicleLocation.builder().levelNumber(levelNumber).vehicleLocationLotNumber(nextParkingLocation).build();
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














