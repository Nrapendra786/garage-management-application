package com.ysuratask.exceptions;

import java.io.IOException;

/**
 * Created by NrapendraKumar on 20-03-2016.
 */
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
