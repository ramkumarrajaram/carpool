package com.sia.carpool.persistance;


import com.sia.carpool.persistance.publishride.PublishRideEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "subscriber_user")
public class SubscribeRideEntity {

    @Id
    private String mobileNumber;
    private String userName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "publisher_number", nullable = false)
    private PublishRideEntity rider;
}
