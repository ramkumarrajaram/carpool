package com.sia.carpool.subscriberide.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscribeRideRequest {

    private String driverMobileNumber;
    private String subscriberMobileNumber;
    private LocalDateTime tripTime;
    private String userName;
    private int totalSeatsRequired;
}
