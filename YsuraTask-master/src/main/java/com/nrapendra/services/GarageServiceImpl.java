package com.nrapendra.services;

import com.nrapendra.entities.ParkingVehicleLocation;
import com.nrapendra.entities.VehicleInformation;
import com.nrapendra.exceptions.FileException;
import com.nrapendra.exceptions.NotFoundException;
import com.nrapendra.models.*;
import com.nrapendra.repositories.ParkingVehicleLocationRespository;
import com.nrapendra.repositories.VehicleRepository;
import com.nrapendra.utils.AppUtil;
import com.nrapendra.utils.NumberUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;

/**
 * Created by NrapendraKumar
 */

@Service
@Slf4j
public class GarageServiceImpl implements GarageService {

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
    private ParkingLotAllocatorService parkingLotAllocatorService;

    @Autowired
    private FileReaderService<GarageSpaceInformation> garageSpaceFileReaderServiceImpl;

    @Autowired
    private ParkingVehicleLocationRespository parkingVehicleLocationRespository;

    @Override
    public VehicleMovementStatus addVehicle(Vehicle vehicle) throws IOException, ParseException {
        String vehicleNumber = vehicle.getVehicleNumber();
        if(isVehicleAlreadyExist(vehicleNumber)) {
            ParkingVehicleLocation parkingVehicleLocation = parkingLotAllocatorService.allocateParkingLot();
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
        if(Objects.isNull(vehicleInformation)) {
            vehicleMovementStatus.setStatus(AppUtil.VEHICLE_DOES_NOT_EXIST);
        }else {
            vehicleMovementStatus.setStatus(AppUtil.VEHICLE_EXITED);
            vehicleRepository.delete(vehicleInformation);
        }
        return vehicleMovementStatus;
    }

    @Override
    public VehicleParkingLocation getVehicleParkingLocation(String vehicleNumber) throws ParseException, FileException {
        VehicleInformation vehicleInformation = vehicleRepository.findByVehicleNumber(vehicleNumber);
        if (vehicleInformation == null) {
            throw new NotFoundException(AppUtil.VEHICLE_DOES_NOT_EXIST);
        }
        ParkingVehicleLocation parkingVehicleLocation = vehicleInformation.getVehicleParkingLocation();
        return (VehicleParkingLocation) vehicleParkingLocationConverterService.convert(parkingVehicleLocation);
    }

    @Override
    public FreeParkingGarageSpace getFreeParkingLotsInformation() throws FileException, ParseException {
        FreeParkingGarageSpace freeParkingGarageSpace = new FreeParkingGarageSpace();
        int noOfOccupiedSpacesInGarage = getNoOfOccupiedSpace();
        int totalSpaceInGarage = getGarageSpace();
        int freeSpaceInGarage = totalSpaceInGarage - noOfOccupiedSpacesInGarage;
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
            noOfOccupiedSpacesInGarage += (int) parkingVehicleLocations.stream().filter
                                          ((ParkingVehicleLocation parkingVehicleLocation) -> parkingVehicleLocation.getLevelNumber().
                                           equals(garageSpaceInformation.getNoOfLevels())).count();
        }
        return noOfOccupiedSpacesInGarage;
    }

    private int getGarageSpace() throws FileException, ParseException {
        List<GarageSpaceInformation> garageSpaceInformationList =  garageSpaceFileReaderServiceImpl.read();
        return garageSpaceInformationList.stream().
                mapToInt(GarageSpaceInformation::getParkingLotPerLevel).sum();
    }

    private boolean isVehicleAlreadyExist(String vehicleNumber) {
        return vehicleRepository.findByVehicleNumber(vehicleNumber) == null ;
    }
}