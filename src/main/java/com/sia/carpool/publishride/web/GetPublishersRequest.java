package com.sia.carpool.publishride.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetPublishersRequest {

    private String origin;
    private String destination;
    private LocalDate tripTime;
    private int totalSeatsRequired;
}
