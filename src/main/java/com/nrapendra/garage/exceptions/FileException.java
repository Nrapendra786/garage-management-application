package com.nrapendra.garage.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

/**
 * Created by NrapendraKumar
 */

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FileException extends IOException {

    public FileException(String message) {
        super(message);
    }
}
