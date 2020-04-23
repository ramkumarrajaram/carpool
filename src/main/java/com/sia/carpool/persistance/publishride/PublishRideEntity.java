package com.sia.carpool.persistance.publishride;

import com.sia.carpool.persistance.subscriberide.SubscribeRideEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "publisher_user")
@IdClass(PublishRideId.class)
public class PublishRideEntity {

    @Id
    private String mobileNumber;
    @Id
    private LocalDateTime tripTime;
    private String origin;
    private String destination;
    private int numberOfSeats;
    private String userName;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "rider")
    private List<SubscribeRideEntity> subscribers;
}
