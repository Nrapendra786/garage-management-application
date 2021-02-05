package com.ysuratasktest;

import com.ysuratask.Application;
import com.ysuratask.utils.UrlUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by NrapendraKumar on 26-03-2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class UrlUtilTest {

    @Test
    public void testUrlUtil(){
        Assert.assertEquals(UrlUtil.ALL_URL,"/**");
        Assert.assertEquals(UrlUtil.GARAGE_ENTER_VEHICLE_URL,"/garage/enterVehicle");
        Assert.assertEquals(UrlUtil.GARAGE_EXIT_VEHICLE_URL,"/garage/exitVehicle/{vehicleNumber}");
        Assert.assertEquals(UrlUtil.GARAGE_GET_VEHICLE_LOCATION_URL,"/garage/getVehicleLocation/{vehicleNumber}");
        Assert.assertEquals(UrlUtil.GARAGE_FREE_SPACE_URL,"/garage/getGarageFreeParkingSpace");
        Assert.assertEquals(UrlUtil.GARAGE_ALL_URL,"/garage/**");
        Assert.assertEquals(UrlUtil.GARAGE_EXIT_VEHICLE_ALL_URL,"/garage/exitVehicle/**");
        Assert.assertEquals(UrlUtil.GARAGE_GET_VEHICLE_LOCATION_ALL_URL,"/garage/getVehicleLocation/{vehicleNumber}/**");
    }
}
