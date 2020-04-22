package com.sia.carpool.publishride.web;

import com.sia.carpool.publishride.GetSubscribersResult;
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
public class GetSubscriberResponse {

    private List<GetSubscribersResult.Subscriber> subscribers;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Subscriber {
        private String userName;
        private String mobileNumber;
        private String origin;
        private String destination;
        private LocalDateTime tripTime;
        private int numberOfSeats;
    }
}