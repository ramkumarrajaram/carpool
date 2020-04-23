package com.sia.carpool.getsubscribertrips;

import com.sia.carpool.getsubscribertrips.web.GetSubscriberTripsResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetSubscriberTripsResult {
    private List<TripDetail> tripDetails;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TripDetail {

        private String origin;
        private String destination;
        private LocalDateTime tripTime;
        private String driverUserName;
        private String driverMobileNumber;
    }
}
