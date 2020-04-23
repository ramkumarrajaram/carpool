package com.sia.carpool.subscriberide;

import com.sia.carpool.CarPoolUnAuthorisedException;
import com.sia.carpool.persistance.publishride.PublishRideEntity;
import com.sia.carpool.persistance.publishride.PublishRideRepository;
import com.sia.carpool.persistance.subscriberide.SubscribeRideEntity;
import com.sia.carpool.persistance.subscriberide.SubscribeRideRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SubscribeRideService {

    private PublishRideRepository publishRideRepository;
    private SubscribeRideRepository subscribeRideRepository;

    public void subscribeRide(SubscribeRideInput rideInput) {

        PublishRideEntity driverData = publishRideRepository.findByMobileNumberAndTripTime(rideInput.getDriverMobileNumber(),
                rideInput.getTripTime());

        if(driverData == null) {
            throw new CarPoolUnAuthorisedException("Unauthorised");
        }

        SubscribeRideEntity subscribeRideEntity = SubscribeRideEntity.builder()
                .mobileNumber(rideInput.getSubscriberMobileNumber())
                .userName(rideInput.getUserName())
                .rider(driverData)
                .build();

        subscribeRideRepository.save(subscribeRideEntity);
    }
}
