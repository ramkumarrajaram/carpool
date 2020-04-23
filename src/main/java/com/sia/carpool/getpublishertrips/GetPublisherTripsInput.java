package com.sia.carpool.getpublishertrips;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetPublisherTripsInput {

    private String mobileNumber;
}
