package com.sia.carpool.getsubscribertrips.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetSubscriberTripsRequest {

    private String mobileNumber;
}
