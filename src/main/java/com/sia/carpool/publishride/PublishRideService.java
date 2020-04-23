package com.sia.carpool.publishride;

import com.sia.carpool.CarPoolBadRequestException;
import com.sia.carpool.CarPoolException;
import com.sia.carpool.CarPoolUnAuthorisedException;
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

    public GetPublishersResult getPublishers(GetPublishersInput input) {

        List<PublishRideEntity> rideEntityList = null;

        if (input.getOrigin() != null && input.getDestination() == null &&
                input.getTripTime() == null) {
            rideEntityList = publishRideRepository.findByOrigin(input.getOrigin());
        } else if (input.getDestination() != null && input.getOrigin() == null &&
                input.getTripTime() == null) {
            rideEntityList = publishRideRepository.findByDestination(input.getOrigin());
        } else if (input.getTripTime() != null && input.getOrigin() == null
                && input.getDestination() == null) {
            rideEntityList = publishRideRepository.getByTripTime(input.getTripTime());
        } else if (input.getOrigin() != null && input.getDestination() != null
                && input.getTripTime() == null) {
            rideEntityList = publishRideRepository
                    .findByOriginAndDestination(input.getOrigin(), input.getDestination());
        } else if (input.getTripTime() != null && input.getOrigin() != null
                && input.getDestination() != null) {
            rideEntityList = publishRideRepository
                    .getOriginAndDestinationAndTripTime(input.getOrigin(),
                            input.getDestination(), input.getTripTime());
        } else {
            throw new CarPoolBadRequestException("Input criteria mismatch");
        }

        if (rideEntityList == null || rideEntityList.isEmpty()) {
            throw new CarPoolException("No riders found");
        }

        List<GetPublishersResult.Publisher> publishers = rideEntityList.stream()
                .map(publishRideEntity -> GetPublishersResult.Publisher
                        .builder()
                        .userName(publishRideEntity.getUserName())
                        .mobileNumber(publishRideEntity.getMobileNumber())
                        .origin(publishRideEntity.getOrigin())
                        .destination(publishRideEntity.getDestination())
                        .numberOfSeats(publishRideEntity.getNumberOfSeats())
                        .tripTime(publishRideEntity.getTripTime())
                        .build()
                ).collect(toList());

        return GetPublishersResult.builder()
                .publishers(publishers)
                .build();
    }
}
