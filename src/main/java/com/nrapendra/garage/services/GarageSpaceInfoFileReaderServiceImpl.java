package com.nrapendra.garage.services;

import com.nrapendra.garage.exceptions.FileException;
import com.nrapendra.garage.models.GarageSpaceInformation;
import com.nrapendra.garage.utils.AppUtil;
import com.nrapendra.garage.utils.NumberUtil;
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
                        String[] garageInfo = line.split(AppUtil.COMMA);
                        GarageSpaceInformation garageInformation = new GarageSpaceInformation(Integer.parseInt(garageInfo[NumberUtil.ZERO]),Integer.parseInt(garageInfo[NumberUtil.ONE]));
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
