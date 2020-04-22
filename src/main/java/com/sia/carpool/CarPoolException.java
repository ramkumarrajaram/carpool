package com.sia.carpool;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CarPoolException extends RuntimeException {
    private String code;

    public CarPoolException(String code, String message) {
        super(message);
        this.code = code;
    }


    public CarPoolException(String message) {
        super(message);
    }
}
