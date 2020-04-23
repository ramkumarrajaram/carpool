package com.sia.carpool.getpublishers.web;

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
public class GetPublishersResponse {

    private List<Publisher> publishers;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Publisher {
        private String userName;
        private String mobileNumber;
        private String origin;
        private String destination;
        private LocalDateTime tripTime;
        private int numberOfSeats;
    }
}
