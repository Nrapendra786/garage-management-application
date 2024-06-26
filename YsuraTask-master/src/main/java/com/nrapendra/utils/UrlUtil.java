package com.nrapendra.utils;

/**
 * Created by NrapendraKumar
 */
public class UrlUtil {
    public static final String GARAGE_ENTER_VEHICLE_URL = "/garage/enterVehicle";
    public static final String GARAGE_EXIT_VEHICLE_URL = "/garage/exitVehicle/{vehicleNumber}";
    public static final String GARAGE_GET_VEHICLE_LOCATION_URL = "/garage/getVehicleLocation/{vehicleNumber}";
    public static final String GARAGE_FREE_SPACE_URL = "/garage/getGarageFreeParkingSpace";
    public static final String GARAGE_EXIT_VEHICLE_ALL_URL = "/garage/exitVehicle/**";
    public static final String ALL_URL = "/**";
    public static final String GARAGE_GET_VEHICLE_LOCATION_ALL_URL = GARAGE_GET_VEHICLE_LOCATION_URL + ALL_URL;
}
