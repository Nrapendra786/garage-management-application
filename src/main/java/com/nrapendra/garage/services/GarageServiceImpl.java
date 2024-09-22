package com.nrapendra.garage.services;

import com.nrapendra.garage.entities.ParkingVehicleLocation;
import com.nrapendra.garage.entities.VehicleInformation;
import com.nrapendra.garage.exceptions.FileException;
import com.nrapendra.garage.exceptions.NotFoundException;
import com.nrapendra.garage.models.*;
import com.nrapendra.garage.repositories.ParkingVehicleLocationRepository;
import com.nrapendra.garage.repositories.VehicleRepository;
import com.nrapendra.garage.utils.AppUtil;
import com.nrapendra.garage.utils.NumberUtil;
import lombok.extern.slf4j.Slf4j;
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

    private final VehicleMovementStatus vehicleMovementStatus;
    private final VehicleRepository vehicleRepository;
    private final ParkingLotAllocatorService parkingLotAllocatorService;
    private final FileReaderService<GarageSpaceInformation> garageSpaceFileReaderServiceImpl;
    private final ParkingVehicleLocationRepository parkingVehicleLocationRepository;
    private final ConverterService<VehicleInformation, Vehicle> vehicleInformationConverterService;
    private final ConverterService<VehicleParkingLocation, ParkingVehicleLocation> vehicleParkingLocationConverterService;

    public GarageServiceImpl(@Qualifier(AppUtil.VEHICLE_INFORMATION_CONVERTER) ConverterService<VehicleInformation, Vehicle> vehicleInformationConverterService,
                             @Qualifier(AppUtil.VEHICLE_PARKING_LOCATION_CONVERTER) ConverterService<VehicleParkingLocation, ParkingVehicleLocation> vehicleParkingLocationConverterService,
                             VehicleMovementStatus vehicleMovementStatus,
                             VehicleRepository vehicleRepository,
                             ParkingLotAllocatorService parkingLotAllocatorService,
                             FileReaderService<GarageSpaceInformation> garageSpaceFileReaderServiceImpl,
                             ParkingVehicleLocationRepository parkingVehicleLocationRepository) {

        this.vehicleMovementStatus = vehicleMovementStatus;
        this.vehicleRepository = vehicleRepository;
        this.parkingLotAllocatorService = parkingLotAllocatorService;
        this.garageSpaceFileReaderServiceImpl = garageSpaceFileReaderServiceImpl;
        this.parkingVehicleLocationRepository = parkingVehicleLocationRepository;
        this.vehicleInformationConverterService = vehicleInformationConverterService;
        this.vehicleParkingLocationConverterService = vehicleParkingLocationConverterService;
    }


    @Override
    public VehicleMovementStatus addVehicle(Vehicle vehicle) throws IOException, ParseException {
        String vehicleNumber = vehicle.getVehicleNumber();
        if (isVehicleAlreadyExist(vehicleNumber)) {
            ParkingVehicleLocation parkingVehicleLocation = parkingLotAllocatorService.allocateParkingLot();
            if (Objects.isNull(parkingVehicleLocation)) {
                vehicleMovementStatus.setStatus(AppUtil.NO_SPACE_FOR_VEHICLE_IN_GARAGE);
            } else {
                VehicleInformation vehicleInformation = vehicleInformationConverterService.convert(vehicle);
                vehicleRepository.save(vehicleInformation);
                vehicleMovementStatus.setStatus(AppUtil.VEHICLE_ENTERED);
            }
        } else {
            vehicleMovementStatus.setStatus(AppUtil.VEHICLE_ALREADY_EXIST);
        }
        return vehicleMovementStatus;
    }

    @Override
    public VehicleMovementStatus removeVehicle(String vehicleNumber) throws IOException, ParseException {
        VehicleInformation vehicleInformation = vehicleRepository.findByVehicleNumber(vehicleNumber);
        if (Objects.isNull(vehicleInformation)) {
            vehicleMovementStatus.setStatus(AppUtil.VEHICLE_DOES_NOT_EXIST);
        } else {
            vehicleMovementStatus.setStatus(AppUtil.VEHICLE_EXITED);
            vehicleRepository.delete(vehicleInformation);
        }
        return vehicleMovementStatus;
    }

    @Override
    public VehicleParkingLocation getVehicleParkingLocation(String vehicleNumber) throws ParseException, FileException {
        VehicleInformation vehicleInformation = vehicleRepository.findByVehicleNumber(vehicleNumber);
        if (Objects.isNull(vehicleInformation)) {
            throw new NotFoundException(AppUtil.VEHICLE_DOES_NOT_EXIST);
        }
        ParkingVehicleLocation parkingVehicleLocation = vehicleInformation.getVehicleParkingLocation();
        return vehicleParkingLocationConverterService.convert(parkingVehicleLocation);
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
        List<ParkingVehicleLocation> parkingVehicleLocations = parkingVehicleLocationRepository.findAll();
        List<GarageSpaceInformation> garageSpaceInformationList = garageSpaceFileReaderServiceImpl.read();
        int noOfOccupiedSpacesInGarage = NumberUtil.ZERO;
        for (GarageSpaceInformation garageSpaceInformation : garageSpaceInformationList) {
            noOfOccupiedSpacesInGarage += (int) parkingVehicleLocations.stream().filter
                    ((ParkingVehicleLocation parkingVehicleLocation) -> parkingVehicleLocation.getLevelNumber().
                            equals(garageSpaceInformation.getNoOfLevels())).count();
        }
        return noOfOccupiedSpacesInGarage;
    }

    private int getGarageSpace() throws FileException, ParseException {
        return garageSpaceFileReaderServiceImpl
                .read()
                .stream()
                .mapToInt(GarageSpaceInformation::getParkingLotPerLevel)
                .sum();
    }

    private boolean isVehicleAlreadyExist(String vehicleNumber) {
        return Objects.isNull(vehicleRepository.findByVehicleNumber(vehicleNumber));
    }
}