package com.nrapendra.garage.repositories;

import com.nrapendra.garage.entities.ParkingVehicleLocation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by NrapendraKumar
 */
public interface ParkingVehicleLocationRepository extends JpaRepository<ParkingVehicleLocation,Long> {
    // Marker Interface
}
