package com.sia.carpool;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CarPoolBadRequestException extends RuntimeException{
    public CarPoolBadRequestException(String message) {
        super(message);
    }
}
