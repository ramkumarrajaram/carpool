package com.sia.carpool.publishride.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetSubscriberRequest {

    private String origin;
    private String destination;
    private LocalDateTime tripTime;
}
