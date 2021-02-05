package com.ysuratask.repositories;

import com.ysuratask.entities.ParkingVehicleLocation;
import com.ysuratask.entities.VehicleInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by NrapendraKumar on 24-03-2016.
 */
public interface ParkingVehicleLocationRespository extends JpaRepository<ParkingVehicleLocation,Long> {
    // Marker Interface
}
