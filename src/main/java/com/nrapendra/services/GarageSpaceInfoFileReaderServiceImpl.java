package com.nrapendra.services;

import com.nrapendra.exceptions.FileException;
import com.nrapendra.models.GarageSpaceInformation;
import com.nrapendra.utils.AppUtil;
import com.nrapendra.utils.NumberUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by NrapendraKumar
 */
@Service
@Slf4j
public class GarageSpaceInfoFileReaderServiceImpl implements FileReaderService<GarageSpaceInformation> {

    @Override
    public List<GarageSpaceInformation> read() throws FileException {
        InputStream inputStream = getClass().getResourceAsStream(AppUtil.GARAGE_SPACE_INFORMATION);
        List<GarageSpaceInformation> garageInformationList = new ArrayList<>();
        try {
            assert inputStream != null;
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line = bufferedReader.readLine();
                while (Objects.nonNull(line)) {
                    line = bufferedReader.readLine();
                    if (Objects.nonNull(line)) {
                        GarageSpaceInformation garageInformation = new GarageSpaceInformation();
                        String[] garageInfo = line.split(AppUtil.COMMA);
                        garageInformation.setParkingLotPerLevel(Integer.parseInt(garageInfo[NumberUtil.ZERO]));
                        garageInformation.setNoOfLevels(Integer.parseInt(garageInfo[NumberUtil.ONE]));
                        garageInformationList.add(garageInformation);
                    }
                }
            }
        } catch (IOException e) {
            log.error("exception cause is : {0}",e.getCause());
            throw new FileException(e.getMessage());
        }
        return garageInformationList;
    }
}
