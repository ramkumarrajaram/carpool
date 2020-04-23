package com.sia.carpool.getsubscribertrips;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetSubscriberTripsInput {

    private String mobileNumber;
}
