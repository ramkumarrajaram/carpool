package com.sia.carpool;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class CarPoolUnAuthorisedException extends RuntimeException{
    public CarPoolUnAuthorisedException(String message) {
        super(message);
    }
}
