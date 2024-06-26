package com.ysuratask.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

/**
 * Created by NrapendraKumar on 20-03-2016.
 */

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FileException extends IOException {
    private String message;

    public FileException() {
        super();
    }

    public FileException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }

}
