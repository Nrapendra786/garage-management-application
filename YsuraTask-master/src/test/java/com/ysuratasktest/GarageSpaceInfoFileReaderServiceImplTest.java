package com.ysuratasktest;

import com.ysuratask.Application;
import com.ysuratask.exceptions.FileException;
import com.ysuratask.models.GarageSpaceInformation;
import com.ysuratask.services.FileReaderService;
import com.ysuratask.services.GarageSpaceInfoFileReaderServiceImpl;
import com.ysuratask.utils.AppUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by NrapendraKumar on 20-03-2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class GarageSpaceInfoFileReaderServiceImplTest {

    @Autowired
    private GarageSpaceInfoFileReaderServiceImpl garageSpaceInfoFileReaderService;

    @Test
    public void testGarageSpaceInformation() throws FileException, ParseException {
        List<GarageSpaceInformation> garageSpaceInformationList =  garageSpaceInfoFileReaderService.read();
        Assert.assertNotNull(String.valueOf(garageSpaceInformationList.get(0).getNoOfLevels()),String.valueOf(1));
        Assert.assertNotNull(String.valueOf(garageSpaceInformationList.get(1).getNoOfLevels()),String.valueOf(2));
        Assert.assertNotNull(String.valueOf(garageSpaceInformationList.get(0).getParkingLotPerLevel()),String.valueOf(5));
        Assert.assertNotNull(String.valueOf(garageSpaceInformationList.get(1).getParkingLotPerLevel()),String.valueOf(5));
    }
}
