package com.nrapendra.garage.repositories;

import com.nrapendra.garage.entities.VehicleInformation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This is a vehicle Repository
 * Created by NrapendraKumar
 */

public interface VehicleRepository extends JpaRepository<VehicleInformation,Long> {
     VehicleInformation findByVehicleNumber(String vehicleNumber);
}
