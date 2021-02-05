package com.ysuratask.entities;

import com.ysuratask.enums.VehicleType;
import com.ysuratask.utils.AppUtil;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by NrapendraKumar on 23-03-2016.
 */

@Entity
@Table(name = "vehicleinformation")
public class VehicleInformation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "vehicle_number")
    private String vehicleNumber;

    @Column(name = "vehicle_type")
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    @Column(name = "vehicle_enter_date")
    @DateTimeFormat(pattern = AppUtil.DATE_FORMAT)
    private Date vehicleEnterDate;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name = "vehicle_parking_location_id", referencedColumnName = "parking_vehicle_location_id")
    private ParkingVehicleLocation vehicleParkingLocationId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Date getVehicleEnterDate() {
        return vehicleEnterDate;
    }

    public void setVehicleEnterDate(Date vehicleEnterDate) {
        this.vehicleEnterDate = vehicleEnterDate;
    }

    public ParkingVehicleLocation getParkingVehicleLocation() {
        return vehicleParkingLocationId;
    }

    public void setParkingVehicleLocation(ParkingVehicleLocation vehicleParkingLocationId) {
        this.vehicleParkingLocationId = vehicleParkingLocationId;
    }
}
