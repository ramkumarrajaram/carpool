package com.sia.carpool.getpublishertrips.web;

import com.sia.carpool.getpublishertrips.GetPublisherTripsService;
import com.sia.carpool.getpublishertrips.GetPublisherTripsInput;
import com.sia.carpool.getpublishertrips.GetPublisherTripsResult;
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
@AllArgsConstructor
@RequestMapping("/get")
public class GetPublisherTripsController {

    private ModelMapper modelMapper;
    private GetPublisherTripsService getPublisherTripsService;

    @PostMapping("/publisher/trips")
    public GetPublisherTripsResponse getSubscribers(@RequestBody GetPublisherTripsRequest request,
                                                    HttpServletResponse response) {
        GetPublisherTripsInput driverInput = modelMapper
                .map(request, GetPublisherTripsInput.class);

        GetPublisherTripsResult result = getPublisherTripsService
                .getSubscribers(driverInput);

        response.setHeader(
                CACHE_CONTROL,
                CacheControl.noStore().getHeaderValue()
        );

        return modelMapper.map(result, GetPublisherTripsResponse.class);
    }
}
