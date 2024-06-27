package com.nrapendra.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

/**
 * Created by NrapendraKumar
 */

@Entity
@Table(name = "parkingvehiclelocation")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
