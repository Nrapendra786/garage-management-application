package com.ysuratask.services;
import com.ysuratask.entities.ParkingVehicleLocation;
import com.ysuratask.entities.VehicleInformation;
import com.ysuratask.exceptions.FileException;
import com.ysuratask.exceptions.NotFoundException;
import com.ysuratask.helpers.ParkingLotAllocator;
import com.ysuratask.models.*;
import com.ysuratask.repositories.ParkingVehicleLocationRespository;
import com.ysuratask.repositories.VehicleRepository;
import com.ysuratask.utils.AppUtil;
import com.ysuratask.utils.NumberUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collector;

import static java.util.stream.Collectors.summingInt;

/**
 * Created by NrapendraKumar on 20-03-2016.
 */

@Service
public class GarageServiceImpl implements GarageService {

    private static final Logger logger = LoggerFactory.getLogger(GarageServiceImpl.class.getName());

    @Autowired
    private VehicleMovementStatus vehicleMovementStatus;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    @Qualifier(AppUtil.VEHICLE_INFORMATION_CONVERTER)
    private ConverterService vehicleInformationConverterService;

    @Autowired
    @Qualifier(AppUtil.VEHICLE_PARKING_LOCATION_CONVERTER)
    private ConverterService vehicleParkingLocationConverterService;

    @Autowired
    private ParkingLotAllocator parkingLotAllocator;

    @Autowired
    private FileReaderService garageSpaceFileReaderServiceImpl;

    @Autowired
    private ParkingVehicleLocationRespository parkingVehicleLocationRespository;

    @Override
    public VehicleMovementStatus addVehicle(Vehicle vehicle) throws IOException, ParseException {
        String vehicleNumber = vehicle.getVehicleNumber();
        if(isVehicleAlreadyExist(vehicleNumber)) {
            ParkingVehicleLocation parkingVehicleLocation = parkingLotAllocator.allocateParkingLot();
            if (parkingVehicleLocation == null) {
                vehicleMovementStatus.setStatus(AppUtil.NO_SPACE_FOR_VEHICLE_IN_GARAGE);
            } else {
                VehicleInformation vehicleInformation = (VehicleInformation) vehicleInformationConverterService.convert(vehicle);
                vehicleRepository.save(vehicleInformation);
                vehicleMovementStatus.setStatus(AppUtil.VEHICLE_ENTERED);
            }
        }else {
            vehicleMovementStatus.setStatus(AppUtil.VEHICLE_ALREADY_EXIST);
        }
        return vehicleMovementStatus;
    }

    @Override
    public VehicleMovementStatus removeVehicle(String vehicleNumber) throws IOException, ParseException {
        VehicleInformation vehicleInformation = vehicleRepository.findByVehicleNumber(vehicleNumber);
        if(vehicleInformation == null){
            vehicleMovementStatus.setStatus(AppUtil.VEHICLE_DOES_NOT_EXIST);
        }else {
            vehicleRepository.delete(vehicleInformation);
            vehicleMovementStatus.setStatus(AppUtil.VEHICLE_EXITED);
        }
        return vehicleMovementStatus;
    }

    @Override
    public VehicleParkingLocation getVehicleParkingLocation(String vehicleNumber) throws ParseException, FileException {
        VehicleInformation vehicleInformation = vehicleRepository.findByVehicleNumber(vehicleNumber);
        if (vehicleInformation == null) {
            throw new NotFoundException(AppUtil.VEHICLE_DOES_NOT_EXIST);
        }
        ParkingVehicleLocation parkingVehicleLocation = vehicleInformation.getParkingVehicleLocation();
        VehicleParkingLocation vehicleParkingLocation = (VehicleParkingLocation) vehicleParkingLocationConverterService.convert(parkingVehicleLocation);
        return vehicleParkingLocation;
    }

    @Override
    public FreeParkingGarageSpace getFreeParkingLotsInformation() throws FileException, ParseException {
        FreeParkingGarageSpace freeParkingGarageSpace = new FreeParkingGarageSpace();
        int noOfOccupiedSpacesInGarage = getNoOfOccupiedSpace();
        int totalSpaceinGarage = getGarageSpace();
        int freeSpaceInGarage = totalSpaceinGarage - noOfOccupiedSpacesInGarage;
        if (freeSpaceInGarage > NumberUtil.ZERO) {
            freeParkingGarageSpace.setNoOfFreeParkingLot(freeSpaceInGarage);
            freeParkingGarageSpace.setParkingSpaceStatus(AppUtil.PARKING_SPACE_AVAILABLE);
        } else {
            freeParkingGarageSpace.setNoOfFreeParkingLot(freeSpaceInGarage);
            freeParkingGarageSpace.setParkingSpaceStatus(AppUtil.PARKING_SPACE_NOT_AVAILABLE);
        }
        return freeParkingGarageSpace;
    }

    private int getNoOfOccupiedSpace() throws FileException, ParseException {
        List<ParkingVehicleLocation> parkingVehicleLocations = parkingVehicleLocationRespository.findAll();
        List<GarageSpaceInformation> garageSpaceInformationList =  garageSpaceFileReaderServiceImpl.read();
        int noOfOccupiedSpacesInGarage = NumberUtil.ZERO;
        for(GarageSpaceInformation garageSpaceInformation : garageSpaceInformationList) {
            noOfOccupiedSpacesInGarage += parkingVehicleLocations.stream().filter
                                          ((ParkingVehicleLocation parkingVehicleLocation) -> parkingVehicleLocation.getLevelNumber().
                                           equals(garageSpaceInformation.getNoOfLevels())).count();
        }
        return noOfOccupiedSpacesInGarage;
    }

    private int getGarageSpace() throws FileException, ParseException {
        List<GarageSpaceInformation> garageSpaceInformationList =  garageSpaceFileReaderServiceImpl.read();
        int totalGarageSpace = garageSpaceInformationList.stream().
                mapToInt((GarageSpaceInformation garbageSpaceInformation) ->
                        garbageSpaceInformation.getParkingLotPerLevel()).sum();
        return totalGarageSpace;
    }

    private boolean isVehicleAlreadyExist(String vehicleNumber) {
        VehicleInformation vehicleInformation = vehicleRepository.findByVehicleNumber(vehicleNumber);
        return vehicleInformation != null ?  false : true;
    }
}