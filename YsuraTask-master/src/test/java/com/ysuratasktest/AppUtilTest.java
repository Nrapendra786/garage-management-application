package com.ysuratasktest;

import com.ysuratask.Application;
import com.ysuratask.utils.AppUtil;
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
public class AppUtilTest {

    @Test
    public void testAppUtil() {
        Assert.assertEquals(AppUtil.COMMA,",");
        Assert.assertEquals(AppUtil.DATE_FORMAT,"yyyy-MM-dd");
        Assert.assertEquals(AppUtil.GARAGE_SPACE_INFORMATION,"/files/garageSpaceInfo.csv");
        Assert.assertEquals(AppUtil.NO_SPACE_FOR_VEHICLE_IN_GARAGE,"No Space For Vehicle in Garage");
        Assert.assertEquals(AppUtil.PARKING_SPACE_AVAILABLE,"Parking Space Available");
        Assert.assertEquals(AppUtil.PARKING_SPACE_NOT_AVAILABLE,"Parking Space Not Available");
        Assert.assertEquals(AppUtil.VEHICLE_ALREADY_EXIST,"Vehicle Already Exist");
        Assert.assertEquals(AppUtil.VEHICLE_ENTERED,"Vehicle Entered");
        Assert.assertEquals(AppUtil.VEHICLE_EXITED,"Vehicle Exited");
        Assert.assertEquals(AppUtil.VEHICLE_NUMBER,"vehicleNumber");
        Assert.assertEquals(AppUtil.VEHICLE_PARKING_LOCATION_CONVERTER,"vehicleParkingLocationConverterImpl");
        Assert.assertEquals(AppUtil.VEHICLE_INFORMATION_CONVERTER,"vehicleInformationConverterImpl");
        Assert.assertEquals(AppUtil.VEHICLE_DOES_NOT_EXIST,"Vehicle Does Not Exist");
        Assert.assertEquals(AppUtil.LEVEL_LIMIT,2);
    }
}
