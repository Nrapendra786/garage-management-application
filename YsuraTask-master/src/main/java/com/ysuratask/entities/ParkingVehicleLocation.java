package com.ysuratask.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by NrapendraKumar on 23-03-2016.
 */

@Entity
@Table(name = "parkingvehiclelocation")
public class ParkingVehicleLocation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "parking_vehicle_location_id")
    private Integer parkingVehicleLocationId;

    @Column(name = "vehicle_location_number")
    private Integer vehicleLocationLotNumber;

    @Column(name = "level_number")
    private Integer levelNumber;

    public Integer getParkingVehicleLocationId() {
        return parkingVehicleLocationId;
    }

    public void setParkingVehicleLocationId(Integer parkingVehicleLocationId) {
        this.parkingVehicleLocationId = parkingVehicleLocationId;
    }

    public Integer getVehicleLocationLotNumber() {
        return vehicleLocationLotNumber;
    }

    public void setVehicleLocationLotNumber(Integer vehicleLocationLotNumber) {
        this.vehicleLocationLotNumber = vehicleLocationLotNumber;
    }

    public Integer getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(Integer levelNumber) {
        this.levelNumber = levelNumber;
    }
}
