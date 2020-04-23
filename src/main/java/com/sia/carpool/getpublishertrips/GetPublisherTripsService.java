package com.sia.carpool.getpublishertrips;

import com.sia.carpool.CarPoolException;
import com.sia.carpool.persistance.publishride.PublishRideEntity;
import com.sia.carpool.persistance.publishride.PublishRideRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class GetPublisherTripsService {

    private PublishRideRepository publishRideRepository;

    public GetPublisherTripsResult getSubscribers(GetPublisherTripsInput driverInput) {
        List<PublishRideEntity> publishRideEntityList = publishRideRepository
                .findByMobileNumber(driverInput.getMobileNumber());

        if(publishRideEntityList != null && !publishRideEntityList.isEmpty()) {
            List<GetPublisherTripsResult.TripDetail> tripDetails = publishRideEntityList.stream()
                    .map(entity -> GetPublisherTripsResult.TripDetail.builder()
                            .origin(entity.getOrigin())
                            .destination(entity.getDestination())
                            .tripTime(entity.getTripTime())
                            .subscribers(entity.getSubscribers()
                                    .stream().map(subscribeRideEntity -> GetPublisherTripsResult.Subscriber
                                            .builder()
                                            .mobileNumber(subscribeRideEntity.getMobileNumber())
                                            .userName(subscribeRideEntity.getUserName())
                                            .build())
                                    .collect(toList())
                            )
                            .build())
                    .collect(toList());

            return GetPublisherTripsResult.builder()
                    .tripDetails(tripDetails)
                    .build();
        } else {
            throw new CarPoolException("No Data found for the mobile number");
        }
    }
}
