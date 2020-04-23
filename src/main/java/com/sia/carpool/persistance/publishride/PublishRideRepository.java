package com.sia.carpool.persistance.publishride;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PublishRideRepository extends JpaRepository<PublishRideEntity, String> {

    List<PublishRideEntity> findByOrigin(String origin);

    List<PublishRideEntity> findByDestination(String destination);

    @Query(value = "select * from publisher_user where trip_time like ?1%", nativeQuery = true)
    List<PublishRideEntity> getByTripTime(LocalDate tripTime);

    List<PublishRideEntity> findByOriginAndDestinationAndNumberOfSeatsGreaterThanEqual(String origin,
                                                                                       String destination, int size);

    PublishRideEntity findByMobileNumberAndTripTime(String mobileNumber, LocalDateTime tripTime);

    List<PublishRideEntity> findByMobileNumber(String mobileNumber);

    @Query(value = "select * from publisher_user where origin = ?1 " +
            "and destination = ?2 and trip_time like ?3% and number_of_seats >= ?4", nativeQuery = true)
    List<PublishRideEntity> getOriginAndDestinationAndTripTime(String origin, String destination,
                                                               LocalDate tripTime, int size);
}
