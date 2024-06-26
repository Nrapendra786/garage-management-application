package com.nrapendra.models;

import lombok.*;

/**
 * Created by NrapendraKumar
 */


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehicleParkingLocation {
    private int levelNumber;
    private int parkingLotNumber;
}
