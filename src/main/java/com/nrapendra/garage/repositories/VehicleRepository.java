package com.nrapendra.garage.repositories;

import com.nrapendra.garage.entities.VehicleInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * This is a vehicle Repository
 * Created by NrapendraKumar
 */

public interface VehicleRepository extends JpaRepository<VehicleInformation,Long> {
     Optional<VehicleInformation> findByVehicleNumber(String vehicleNumber);
}


