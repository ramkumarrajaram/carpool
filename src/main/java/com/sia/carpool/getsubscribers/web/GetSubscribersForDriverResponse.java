package com.sia.carpool.getsubscribers.web;

import com.sia.carpool.getsubscribers.GetSubscribersForDriverResult;
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
public class GetSubscribersForDriverResponse {

    private List<TripDetail> tripDetails;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TripDetail {
        private String origin;
        private String destination;
        private LocalDateTime tripTime;

        private List<GetSubscribersForDriverResult.Subscriber> subscribers;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Subscriber {
        private String userName;
        private String mobileNumber;

    }
}
