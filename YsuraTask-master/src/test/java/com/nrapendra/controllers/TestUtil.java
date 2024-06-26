package com.nrapendra.controllers;

public class TestUtil {

    public static final String CORRECT_USER= "manager";
    public static final String CORRECT_PASSWORD= "manager";
    public static final String INCORRECT_USER= "user";
    public static final String INCORRECT_PASSWORD= "user";

    public static final String GARAGE_ENTER_VEHICLE_URL = "/garage/enterVehicle";
    public static final String GARAGE_EXIT_VEHICLE_URL = "/garage/exitVehicle/";
    public static final String GARAGE_GET_VEHICLE_LOCATION_URL = "/garage/getVehicleLocation/";
    public static final String GARAGE_FREE_SPACE_URL = "/garage/getGarageFreeParkingSpace";
    public static final String GARAGE_ALL_URL = "/garage/**";
    public static final String GARAGE_EXIT_VEHICLE_ALL_URL = "/garage/exitVehicle/**";
    public static final String ALL_URL = "/**";
    public static final String GARAGE_GET_VEHICLE_LOCATION_ALL_URL = GARAGE_GET_VEHICLE_LOCATION_URL + ALL_URL;

    public static final String PARKING_SPACE_AVAILABLE = "Parking Space Available";
    public static final String PARKING_SPACE_NOT_AVAILABLE = "Parking Space Not Available";

    public static final String VEHICLE_ENTERED = "Vehicle Entered";
    public static final String VEHICLE_ALREADY_EXIST = "Vehicle Already Exist";
    public static final String VEHICLE_EXITED = "Vehicle Exited";
    public static final String VEHICLE_DOES_NOT_EXIST = "Vehicle Does Not Exist";
}
