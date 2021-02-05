package com.ysuratask.exceptions;

/**
 * Created by NrapendraKumar on 20-03-2016.
 */
public class NotFoundException extends RuntimeException {

    private String message;

    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}


