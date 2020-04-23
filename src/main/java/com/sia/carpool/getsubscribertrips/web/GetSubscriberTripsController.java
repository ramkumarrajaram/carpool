package com.sia.carpool.getsubscribertrips.web;

import com.sia.carpool.getsubscribertrips.GetSubscriberTripsInput;
import com.sia.carpool.getsubscribertrips.GetSubscriberTripsResult;
import com.sia.carpool.getsubscribertrips.GetSubscriberTripsService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@AllArgsConstructor
@RequestMapping("/get")
public class GetSubscriberTripsController {

    private ModelMapper modelMapper;
    private GetSubscriberTripsService getSubscriberTripsService;

    @PostMapping("/subscriber/trips")
    public GetSubscriberTripsResponse getSubscriberTrips(
            @RequestBody GetSubscriberTripsRequest request,
            HttpServletResponse response) {

        GetSubscriberTripsInput input = modelMapper.map(request, GetSubscriberTripsInput.class);

        GetSubscriberTripsResult result = getSubscriberTripsService.getSubscriberTrips(input);

        return modelMapper.map(result, GetSubscriberTripsResponse.class);
    }
}
