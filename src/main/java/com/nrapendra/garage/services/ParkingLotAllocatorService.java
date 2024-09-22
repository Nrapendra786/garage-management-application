package com.nrapendra.garage.services;

import com.nrapendra.garage.entities.ParkingVehicleLocation;
import com.nrapendra.garage.exceptions.FileException;
import com.nrapendra.garage.models.GarageSpaceInformation;
import com.nrapendra.garage.repositories.ParkingVehicleLocationRepository;
import com.nrapendra.garage.utils.AppUtil;
import com.nrapendra.garage.utils.NumberUtil;
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

    private ParkingVehicleLocationRepository parkingVehicleLocationRepository;

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
        return (int) parkingVehicleLocationRepository.findAll().
                              stream().filter((ParkingVehicleLocation p) -> p.getLevelNumber() == levelNo).count();
    }

    private boolean isFreeSpaceAvailableInGarage(int spaceForNoOfVehicle,int filledPositionByVehicle,int leveleNo) {
        return spaceForNoOfVehicle - filledPositionByVehicle > NumberUtil.ZERO && leveleNo <= AppUtil.LEVEL_LIMIT;
    }
}














