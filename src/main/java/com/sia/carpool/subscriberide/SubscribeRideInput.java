package com.sia.carpool.subscriberide;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscribeRideInput {

    private String driverMobileNumber;
    private String subscriberMobileNumber;
    private LocalDateTime tripTime;
    private String userName;
    private int totalSeatsRequired;
}
