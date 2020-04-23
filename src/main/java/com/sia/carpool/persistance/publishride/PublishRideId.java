package com.sia.carpool.persistance.publishride;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublishRideId implements Serializable {

    private String mobileNumber;
    private LocalDateTime tripTime;
}
