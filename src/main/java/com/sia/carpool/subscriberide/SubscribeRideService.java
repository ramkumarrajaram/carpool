package com.sia.carpool.subscriberide;

import com.sia.carpool.CarPoolUnAuthorisedException;
import com.sia.carpool.persistance.publishride.PublishRideEntity;
import com.sia.carpool.persistance.publishride.PublishRideRepository;
import com.sia.carpool.persistance.registeruser.RegisterUserEntity;
import com.sia.carpool.persistance.registeruser.RegisterUserRepository;
import com.sia.carpool.persistance.subscriberide.SubscribeRideEntity;
import com.sia.carpool.persistance.subscriberide.SubscribeRideRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SubscribeRideService {

    private RegisterUserRepository registerUserRepository;
    private PublishRideRepository publishRideRepository;
    private SubscribeRideRepository subscribeRideRepository;

    public void subscribeRide(SubscribeRideInput rideInput) {

        RegisterUserEntity subscriberUserEntity = registerUserRepository
                .findByMobileNumber(rideInput.getSubscriberMobileNumber());

        if(subscriberUserEntity== null) {
            throw new CarPoolUnAuthorisedException("Unauthorised subscriber");
        }

        PublishRideEntity driverData = publishRideRepository
                .findByMobileNumberAndTripTime(rideInput.getDriverMobileNumber(),
                        rideInput.getTripTime());

        if (driverData == null) {
            throw new CarPoolUnAuthorisedException("Unauthorised Driver" );
        }

        SubscribeRideEntity subscribeRideEntity = SubscribeRideEntity.builder()
                .mobileNumber(rideInput.getSubscriberMobileNumber())
                .userName(rideInput.getUserName())
                .rider(driverData)
                .build();

        subscribeRideRepository.save(subscribeRideEntity);
        driverData.setNumberOfSeats(driverData.getNumberOfSeats() - 1);

        publishRideRepository.save(driverData);
    }
}
