package com.sia.carpool.publishride.web;

import com.sia.carpool.publishride.GetSubscribersInput;
import com.sia.carpool.publishride.GetSubscribersResult;
import com.sia.carpool.publishride.PublishRideInput;
import com.sia.carpool.publishride.PublishRideService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.CacheControl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.HttpHeaders.CACHE_CONTROL;

@RestController
@AllArgsConstructor
@RequestMapping("/ride")
public class PublishRideController {

    private ModelMapper modelMapper;
    private PublishRideService publishRideService;

    @PostMapping("/publish")
    public void publishRide(HttpServletRequest request,
                            @RequestBody PublishRideRequest publishRideRequest,
                            HttpServletResponse response) {
        PublishRideInput rideInput = modelMapper
                .map(publishRideRequest, PublishRideInput.class);

        publishRideService.publishRide(rideInput);

        response.setHeader(
                CACHE_CONTROL,
                CacheControl.noStore().getHeaderValue()
        );

        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    @PostMapping("/getsubscribers")
    public GetSubscriberResponse getSubscribers(
            HttpServletRequest request,
            @RequestBody GetSubscriberRequest publishRideRequest,
            HttpServletResponse response) {
        GetSubscribersInput subscribersInput = modelMapper
                .map(publishRideRequest, GetSubscribersInput.class);

        GetSubscribersResult subscribersResult = publishRideService.getSubcribers(subscribersInput);

        response.setHeader(
                CACHE_CONTROL,
                CacheControl.noStore().getHeaderValue()
        );

        return modelMapper.map(subscribersResult, GetSubscriberResponse.class);
    }
}
