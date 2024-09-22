package com.nrapendra.garage.services;

import com.nrapendra.garage.exceptions.FileException;
import org.springframework.stereotype.Service;

import java.text.ParseException;

/**
 * Created by NrapendraKumar
 */
@Service
public interface ConverterService<T,U> {
    T convert(U u) throws ParseException, FileException;
}
