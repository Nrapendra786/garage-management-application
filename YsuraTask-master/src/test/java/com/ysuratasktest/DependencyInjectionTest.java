package com.ysuratasktest;

import com.ysuratask.Application;
import com.ysuratask.configs.SecurityConfig;
import com.ysuratask.controllers.GarageController;
import com.ysuratask.helpers.ParkingLotAllocator;
import com.ysuratask.services.ConverterService;
import com.ysuratask.services.FileReaderService;
import com.ysuratask.services.GarageService;
import com.ysuratask.utils.AppUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by NrapendraKumar on 26-03-2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class DependencyInjectionTest {

    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private GarageController garageController;

    @Autowired
    private GarageService garageService;

    @Autowired
    private FileReaderService fileReaderService;

    @Autowired
    @Qualifier(AppUtil.VEHICLE_PARKING_LOCATION_CONVERTER)
    private ConverterService vehicleParkingLocationConverterServiceImpl;

    @Autowired
    @Qualifier(AppUtil.VEHICLE_INFORMATION_CONVERTER)
    private ConverterService vehicleInformationConverterServiceImpl;

    @Autowired
    private ParkingLotAllocator parkingLotAllocator;

    @Test
    public void testDependencyInjection() {
        Assert.assertNotNull(securityConfig);
        Assert.assertNotNull(garageController);
        Assert.assertNotNull(garageService);
        Assert.assertNotNull(fileReaderService);
        Assert.assertNotNull(vehicleParkingLocationConverterServiceImpl);
        Assert.assertNotNull(vehicleInformationConverterServiceImpl);
        Assert.assertNotNull(parkingLotAllocator);
    }
}
