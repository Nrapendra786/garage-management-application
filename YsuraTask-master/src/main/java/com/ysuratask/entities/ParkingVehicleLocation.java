package com.ysuratask.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by NrapendraKumar on 23-03-2016.
 */

@Entity
@Table(name = "parkingvehiclelocation")
@Setter
@Getter
public class ParkingVehicleLocation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "parking_vehicle_location_id")
    private Integer parkingVehicleLocationId;

    @Column(name = "vehicle_location_number")
    private Integer vehicleLocationLotNumber;

    @Column(name = "level_number")
    private Integer levelNumber;
}
