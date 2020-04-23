package com.sia.carpool.subscriberide.web;

import com.sia.carpool.subscriberide.SubscribeRideInput;
import com.sia.carpool.subscriberide.SubscribeRideService;
import com.sia.carpool.subscriberide.web.SubscribeRideRequest;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.CacheControl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.HttpHeaders.CACHE_CONTROL;

@RestController
@RequestMapping("/ride")
@AllArgsConstructor
public class SubscribeRideController {

    private ModelMapper modelMapper;
    private SubscribeRideService subscribeRideService;

    @PostMapping("/subscribe")
    public void subscribeRide(@RequestBody SubscribeRideRequest rideRequest,
                              HttpServletResponse response) {
        SubscribeRideInput rideInput = modelMapper.map(rideRequest, SubscribeRideInput.class);

        subscribeRideService.subscribeRide(rideInput);

        response.setHeader(
                CACHE_CONTROL,
                CacheControl.noStore().getHeaderValue()
        );

        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}
