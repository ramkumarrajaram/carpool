package com.sia.carpool.getsubscribertrips;

import com.sia.carpool.persistance.subscriberide.SubscribeRideEntity;
import com.sia.carpool.persistance.subscriberide.SubscribeRideRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.*;

@Service
@AllArgsConstructor
public class GetSubscriberTripsService {

    private SubscribeRideRepository subscribeRideRepository;

    public GetSubscriberTripsResult getSubscriberTrips(GetSubscriberTripsInput input) {

        List<SubscribeRideEntity> subscribeRideEntityList = subscribeRideRepository
                .findByMobileNumber(input.getMobileNumber());

        List<GetSubscriberTripsResult.TripDetail> tripDetails = subscribeRideEntityList.stream()
                .map(entity -> GetSubscriberTripsResult.TripDetail
                        .builder()
                        .origin(entity.getRider().getOrigin())
                        .destination(entity.getRider().getDestination())
                        .driverMobileNumber(entity.getRider().getMobileNumber())
                        .driverUserName(entity.getRider().getUserName())
                        .tripTime(entity.getRider().getTripTime())
                        .build())
                .collect(toList());

        return GetSubscriberTripsResult.builder()
                .tripDetails(tripDetails)
                .build();
    }
}
