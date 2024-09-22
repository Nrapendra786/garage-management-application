package com.nrapendra.garage.entities;

import com.nrapendra.garage.enums.VehicleType;
import com.nrapendra.garage.utils.AppUtil;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by NrapendraKumar
 */

@Entity
@Table(name = "vehicleinformation")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehicleInformation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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
    private ParkingVehicleLocation vehicleParkingLocation;
}
