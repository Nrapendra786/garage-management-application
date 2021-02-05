package com.ysuratask.repositories;

import com.ysuratask.entities.VehicleInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * This is a vehicle Repository
 * Created by NrapendraKumar on 23-03-2016.
 */

public interface VehicleRepository extends CrudRepository<VehicleInformation,Long> {
    public VehicleInformation findByVehicleNumber(String vehicleNumber);
}
