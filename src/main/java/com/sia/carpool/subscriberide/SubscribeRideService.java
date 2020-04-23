package com.sia.carpool.subscriberide;

import com.sia.carpool.CarPoolException;
import com.sia.carpool.CarPoolUnAuthorisedException;
import com.sia.carpool.persistance.publishride.PublishRideEntity;
import com.sia.carpool.persistance.publishride.PublishRideRepository;
import com.sia.carpool.persistance.registeruser.RegisterUserEntity;
import com.sia.carpool.persistance.registeruser.RegisterUserRepository;
import com.sia.carpool.persistance.subscriberide.SubscribeRideEntity;
import com.sia.carpool.persistance.subscriberide.SubscribeRideRepository;
import com.sia.carpool.subscriberide.fcm.PushNotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SubscribeRideService {

    private RegisterUserRepository registerUserRepository;
    private PublishRideRepository publishRideRepository;
    private SubscribeRideRepository subscribeRideRepository;
    private final PushNotificationClient pushNotificationClient;

    public void subscribeRide(SubscribeRideInput rideInput) {

        RegisterUserEntity subscriberUserEntity = registerUserRepository
                .findByMobileNumber(rideInput.getSubscriberMobileNumber());

        if(subscriberUserEntity== null) {
            throw new CarPoolUnAuthorisedException("Unauthorised subscriber");
        }

        RegisterUserEntity publisherUserEntity = registerUserRepository
                .findByMobileNumber(rideInput.getDriverMobileNumber());

        PublishRideEntity driverData = publishRideRepository
                .findByMobileNumberAndTripTime(rideInput.getDriverMobileNumber(),
                        rideInput.getTripTime());

        if (publisherUserEntity == null || driverData == null) {
            throw new CarPoolUnAuthorisedException("Unauthorised Driver" );
        }

        if(rideInput.getTotalSeatsRequired() > driverData.getNumberOfSeats()) {
            throw new CarPoolException("Insufficient seats");
        }

        SubscribeRideEntity subscribeRideEntity = SubscribeRideEntity.builder()
                .mobileNumber(rideInput.getSubscriberMobileNumber())
                .userName(rideInput.getUserName())
                .rider(driverData)
                .build();

        subscribeRideRepository.save(subscribeRideEntity);

        driverData.setNumberOfSeats(driverData.getNumberOfSeats() - rideInput.getTotalSeatsRequired());

        publishRideRepository.save(driverData);

        try {
            PushNotificationRequest notificationRequest = PushNotificationRequest.builder()
                    .to(publisherUserEntity.getToken())
                    .notification(PushNotificationRequest.Notification.builder()
                            .body("An user named" + rideInput.getUserName() + "has subscribed to your ride. " +
                                    "Please contact the user at " + rideInput.getSubscriberMobileNumber())
                            .title("Cabme")
                            .build())
                    .build();

            pushNotificationClient.pushNotification(
                    "key=AAAAEKULJfY:APA91bEFBxka_6K4ExgbM8GfW02ulT29UXi92QyInx0gYGhR4fM2OAlXRNpcyTtzTIAB2EdAhuvCVg2fYtaU2WwYz8hkCZJge9pwEQRZdZhgejG0qOskqtzSKNAvWf3cbfFy6l43TimQ",
                    notificationRequest);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
