package com.sia.carpool.publishride;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetSubscribersInput {

    private String origin;
    private String destination;
    private LocalDateTime tripTime;
}
