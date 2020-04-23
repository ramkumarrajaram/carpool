package com.sia.carpool.getsubscribers;

import com.sia.carpool.CarPoolException;
import com.sia.carpool.persistance.publishride.PublishRideEntity;
import com.sia.carpool.persistance.publishride.PublishRideRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class GetSubscriberForDriverService {

    private PublishRideRepository publishRideRepository;

    public GetSubscribersForDriverResult getSubscribers(GetSubscribersForDriverInput driverInput) {
        List<PublishRideEntity> publishRideEntityList = publishRideRepository
                .findByMobileNumber(driverInput.getMobileNumber());

        if(publishRideEntityList != null && !publishRideEntityList.isEmpty()) {
            List<GetSubscribersForDriverResult.TripDetail> tripDetails = publishRideEntityList.stream()
                    .map(entity -> GetSubscribersForDriverResult.TripDetail.builder()
                            .origin(entity.getOrigin())
                            .destination(entity.getDestination())
                            .tripTime(entity.getTripTime())
                            .subscribers(entity.getSubscribers()
                                    .stream().map(subscribeRideEntity -> GetSubscribersForDriverResult.Subscriber
                                            .builder()
                                            .mobileNumber(subscribeRideEntity.getMobileNumber())
                                            .userName(subscribeRideEntity.getUserName())
                                            .build())
                                    .collect(toList())
                            )
                            .build())
                    .collect(toList());

            return GetSubscribersForDriverResult.builder()
                    .tripDetails(tripDetails)
                    .build();
        } else {
            throw new CarPoolException("No Data found for the mobile number");
        }
    }
}
