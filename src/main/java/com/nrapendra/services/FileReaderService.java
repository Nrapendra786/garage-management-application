package com.nrapendra.services;

import com.nrapendra.exceptions.FileException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

/**
 * Created by NrapendraKumar
 */
@Service
 interface FileReaderService<T> {
     List<T> read() throws FileException, ParseException;
}
