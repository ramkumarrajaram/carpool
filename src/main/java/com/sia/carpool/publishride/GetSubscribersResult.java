package com.sia.carpool.publishride;

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
public class GetSubscribersResult {

    private List<Subscriber> subscribers;

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
