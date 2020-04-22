package com.sia.carpool.persistance.publishride;

import com.sia.carpool.persistance.registeruser.RegisterUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PublishRideRepository extends JpaRepository<PublishRideEntity, String> {

    List<PublishRideEntity> findByOriginAndDestinationAndTripTime(String origin, String destination ,LocalDate tripTime);
}
