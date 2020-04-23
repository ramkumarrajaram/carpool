package com.sia.carpool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CarPoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarPoolApplication.class, args);
    }
}