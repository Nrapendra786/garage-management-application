package com.nrapendra.repositories;

import com.nrapendra.entities.VehicleInformation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This is a vehicle Repository
 * Created by NrapendraKumar
 */

public interface VehicleRepository extends JpaRepository<VehicleInformation,Long> {
     VehicleInformation findByVehicleNumber(String vehicleNumber);
}
