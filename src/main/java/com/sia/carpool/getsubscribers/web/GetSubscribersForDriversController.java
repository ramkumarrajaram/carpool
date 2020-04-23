package com.sia.carpool.getsubscribers.web;

import com.sia.carpool.getsubscribers.GetSubscriberForDriverService;
import com.sia.carpool.getsubscribers.GetSubscribersForDriverInput;
import com.sia.carpool.getsubscribers.GetSubscribersForDriverResult;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/get")
public class GetSubscribersForDriversController {

    private ModelMapper modelMapper;
    private GetSubscriberForDriverService getSubscriberForDriverService;

    @PostMapping("/subscribers")
    public GetSubscribersForDriverResponse getSubscribers(@RequestBody GetSubscribersForDriverRequest request,
                               HttpServletResponse response) {
        GetSubscribersForDriverInput driverInput = modelMapper
                .map(request, GetSubscribersForDriverInput.class);

        GetSubscribersForDriverResult result = getSubscriberForDriverService
                .getSubscribers(driverInput);

         return modelMapper.map(result, GetSubscribersForDriverResponse.class);
    }
}
