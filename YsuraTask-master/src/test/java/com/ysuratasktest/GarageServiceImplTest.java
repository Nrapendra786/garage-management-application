package com.ysuratasktest;

import com.ysuratask.Application;
import com.ysuratask.enums.VehicleType;
import com.ysuratask.exceptions.FileException;
import com.ysuratask.models.FreeParkingGarageSpace;
import com.ysuratask.models.Vehicle;
import com.ysuratask.models.VehicleMovementStatus;
import com.ysuratask.models.VehicleParkingLocation;
import com.ysuratask.services.GarageService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.io.IOException;
import java.text.ParseException;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by NrapendraKumar on 26-03-2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class GarageServiceImplTest {

    @Mock
    private GarageService garageService;

    @Mock
    private VehicleMovementStatus vehicleMovementStatus;

    @Before
    public final void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddVehicle() throws IOException, ParseException {
        Vehicle vehicle = mock(Vehicle.class);
        vehicle.setVehicleNumber("AB-1239");
        vehicle.setVehicleType(VehicleType.CAR);
        when(garageService.addVehicle(vehicle)).thenReturn(vehicleMovementStatus);
    }

    @Test
    public void testRemoveVehicle() throws IOException, ParseException {
        String vehicleId = "AB-1241";
        when(garageService.removeVehicle(vehicleId)).thenReturn(vehicleMovementStatus);
    }

    @Test
    public void testVehicleParkingLocation() throws ParseException, FileException {
        String vehicleId = "AB-1242";
        VehicleParkingLocation vehicleParkingLocation = mock(VehicleParkingLocation.class);
        when(garageService.getVehicleParkingLocation(vehicleId)).thenReturn(vehicleParkingLocation);
    }

    @Test
    public void testParkingLotInformation() throws ParseException, FileException {
        String vehicleId = "AB-1242";
        FreeParkingGarageSpace freeParkingGarageSpace = mock(FreeParkingGarageSpace.class);
        when(garageService.getFreeParkingLotsInformation()).thenReturn(freeParkingGarageSpace);
    }
}
