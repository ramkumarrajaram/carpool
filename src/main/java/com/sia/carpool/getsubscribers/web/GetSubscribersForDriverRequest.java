package com.sia.carpool.getsubscribers.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetSubscribersForDriverRequest {

    private String mobileNumber;
}
