package com.ysuratask.services;

import com.ysuratask.exceptions.FileException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

/**
 * Created by NrapendraKumar on 20-03-2016.
 */
@Service
public interface FileReaderService<T> {
    public List<T> read() throws FileException, ParseException;
}
