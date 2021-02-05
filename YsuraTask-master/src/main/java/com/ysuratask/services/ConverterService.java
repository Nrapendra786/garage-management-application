package com.ysuratask.services;

import com.ysuratask.exceptions.FileException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.ParseException;

/**
 * Created by NrapendraKumar on 25-03-2016.
 */
@Service
public interface ConverterService<T,U> {
    public T convert(U u) throws ParseException, FileException;
}
