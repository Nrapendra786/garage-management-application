package com.ysuratask.helpers;

import com.ysuratask.entities.ParkingVehicleLocation;
import com.ysuratask.entities.VehicleInformation;
import com.ysuratask.exceptions.FileException;
import com.ysuratask.models.GarageSpaceInformation;
import com.ysuratask.repositories.ParkingVehicleLocationRespository;
import com.ysuratask.services.FileReaderService;
import com.ysuratask.utils.AppUtil;
import com.ysuratask.utils.NumberUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to allocate ParkingLot
 * Created by NrapendraKumar on 21-03-2016.
 */

@Component
public class ParkingLotAllocator {

    private static final Logger logger = Logger.getLogger(ParkingLotAllocator.class.getName());

    @Autowired
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
        int filledPosition = (int)parkingVehicleLocationRespository.findAll().
                              stream().filter((ParkingVehicleLocation p) -> p.getLevelNumber() == levelNo).count();
        return filledPosition;
    }

    private boolean isFreeSpaceAvailableInGarage(int spaceForNoOfVehicle,int filledPositionByVehicle,int leveleNo) {
        return spaceForNoOfVehicle - filledPositionByVehicle > NumberUtil.ZERO && leveleNo <= AppUtil.LEVEL_LIMIT;
    }
}














