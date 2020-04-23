package com.sia.carpool.getpublishertrips.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetPublisherTripsRequest {

    private String mobileNumber;
}
