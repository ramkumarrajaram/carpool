package com.sia.carpool.persistance.publishride;

import com.sia.carpool.persistance.SubscribeRideEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "publisher_user")
public class PublishRideEntity {

    @Id
    private String mobileNumber;
    private String origin;
    private String destination;
    private LocalDateTime tripTime;
    private int numberOfSeats;
    private String userName;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "rider")
    private List<SubscribeRideEntity> subscribers;
}
