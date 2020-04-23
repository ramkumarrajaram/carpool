package com.sia.carpool.persistance.subscriberide;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscribeRideRepository extends JpaRepository<SubscribeRideEntity, String> {

}
