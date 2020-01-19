package com.ivanturkin.cloud.app.taco.api.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
