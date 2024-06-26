package com.nrapendra.services;

import com.nrapendra.exceptions.FileException;
import org.springframework.stereotype.Service;

import java.text.ParseException;

/**
 * Created by NrapendraKumar
 */
@Service
public interface ConverterService<T,U> {
    T convert(U u) throws ParseException, FileException;
}
