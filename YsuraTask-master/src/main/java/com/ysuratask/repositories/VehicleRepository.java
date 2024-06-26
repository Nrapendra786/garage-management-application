package com.ysuratask.repositories;

import com.ysuratask.entities.VehicleInformation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This is a vehicle Repository
 * Created by NrapendraKumar on 23-03-2016.
 */

public interface VehicleRepository extends JpaRepository<VehicleInformation,Long> {
     VehicleInformation findByVehicleNumber(String vehicleNumber);
}
