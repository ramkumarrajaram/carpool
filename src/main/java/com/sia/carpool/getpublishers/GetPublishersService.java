package com.sia.carpool.getpublishers;

import com.sia.carpool.CarPoolBadRequestException;
import com.sia.carpool.CarPoolException;
import com.sia.carpool.persistance.publishride.PublishRideEntity;
import com.sia.carpool.persistance.publishride.PublishRideRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class GetPublishersService {

    private PublishRideRepository publishRideRepository;

    public GetPublishersResult getPublishers(GetPublishersInput input) {

        List<PublishRideEntity> rideEntityList;

        if (input.getOrigin() != null && input.getDestination() != null
                && input.getTripTime() == null) {
            rideEntityList = publishRideRepository
                    .findByOriginAndDestinationAndNumberOfSeatsGreaterThanEqual(input.getOrigin(),
                            input.getDestination(), input.getTotalSeatsRequired());
        } else if (input.getTripTime() != null && input.getOrigin() != null
                && input.getDestination() != null) {
            rideEntityList = publishRideRepository
                    .getOriginAndDestinationAndTripTime(input.getOrigin(),
                            input.getDestination(), input.getTripTime(), input.getTotalSeatsRequired());
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
