package com.nrapendra.garage.services;

import com.nrapendra.garage.exceptions.FileException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

/**
 * Created by NrapendraKumar
 */
@Service
public interface FileReaderService<T> {
     List<T> read() throws FileException, ParseException;
}
