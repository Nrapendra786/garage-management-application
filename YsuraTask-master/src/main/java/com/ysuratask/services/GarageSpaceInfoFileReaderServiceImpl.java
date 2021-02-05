package com.ysuratask.services;

import com.ysuratask.exceptions.FileException;
import com.ysuratask.models.GarageSpaceInformation;
import com.ysuratask.utils.AppUtil;
import com.ysuratask.utils.NumberUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NrapendraKumar on 20-03-2016.
 */
@Service
public class GarageSpaceInfoFileReaderServiceImpl implements FileReaderService<GarageSpaceInformation> {

    @Override
    public List<GarageSpaceInformation> read() throws FileException {
        InputStream inputStream = getClass().getResourceAsStream(AppUtil.GARAGE_SPACE_INFORMATION);
        List<GarageSpaceInformation> garageInformationList = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line = bufferedReader.readLine();
            while (IsNotNull(line)) {
                line = bufferedReader.readLine();
                if (IsNotNull(line)) {
                    GarageSpaceInformation garageInformation = new GarageSpaceInformation();
                    String[] garageInfo = line.split(AppUtil.COMMA);
                    garageInformation.setParkingLotPerLevel(Integer.parseInt(garageInfo[NumberUtil.ZERO]));
                    garageInformation.setNoOfLevels(Integer.parseInt(garageInfo[NumberUtil.ONE]));
                    garageInformationList.add(garageInformation);
                }
            }
        } catch (IOException e) {
            throw new FileException();
        }
        return garageInformationList;
    }

    private boolean IsNotNull(String line){
        return line != null;
    }
}
