package com.nrapendra.repositories;

import com.nrapendra.entities.ParkingVehicleLocation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by NrapendraKumar
 */
public interface ParkingVehicleLocationRespository extends JpaRepository<ParkingVehicleLocation,Long> {
    // Marker Interface
}
