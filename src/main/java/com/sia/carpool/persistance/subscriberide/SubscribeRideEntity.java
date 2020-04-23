package com.sia.carpool.persistance.subscriberide;


import com.sia.carpool.persistance.publishride.PublishRideEntity;
import lombok.*;

import javax.persistence.*;


@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "subscriber_user")
public class SubscribeRideEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mobileNumber;
    private String userName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "publisher_number", nullable = false),
            @JoinColumn(name = "publisher_trip_time", nullable = false)
    })
    private PublishRideEntity rider;
}
