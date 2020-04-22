package com.sia.carpool.publishride;

import com.sia.carpool.CarPoolException;
import com.sia.carpool.persistance.publishride.PublishRideEntity;
import com.sia.carpool.persistance.publishride.PublishRideRepository;
import com.sia.carpool.persistance.registeruser.RegisterUserEntity;
import com.sia.carpool.persistance.registeruser.RegisterUserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class PublishRideService {


    private ModelMapper modelMapper;
    private RegisterUserRepository registerUserRepository;
    private PublishRideRepository publishRideRepository;

    public void publishRide(PublishRideInput rideInput) {
        PublishRideEntity publishRideEntity = modelMapper
                .map(rideInput, PublishRideEntity.class);

        RegisterUserEntity userEntity = registerUserRepository.findByMobileNumber(rideInput.getMobileNumber());

        if (null == userEntity) {
            throw new CarPoolException("User not registered");
        }
        publishRideEntity.setUserName(userEntity.getUserName());

        try {
            publishRideRepository.save(publishRideEntity);
        } catch (Exception e) {
            throw new CarPoolException("Error while saving the driver details " + e.getMessage());
        }
    }

    public GetSubscribersResult getSubcribers(GetSubscribersInput input) {
        List<PublishRideEntity> rideEntityList = publishRideRepository
                .getOriginAndDestinationAndTripTime(input.getOrigin(),
                        input.getDestination(), input.getTripTime());

        if(rideEntityList == null || rideEntityList.isEmpty()) {
            throw new CarPoolException("No riders found");
        }

        List<GetSubscribersResult.Subscriber> subscribers = rideEntityList.stream()
                .map(publishRideEntity -> GetSubscribersResult.Subscriber
                        .builder()
                        .userName(publishRideEntity.getUserName())
                        .mobileNumber(publishRideEntity.getMobileNumber())
                        .origin(publishRideEntity.getOrigin())
                        .destination(publishRideEntity.getDestination())
                        .numberOfSeats(publishRideEntity.getNumberOfSeats())
                        .tripTime(publishRideEntity.getTripTime())
                        .build()
                ).collect(toList());

        return GetSubscribersResult.builder()
                .subscribers(subscribers)
                .build();
    }
}
