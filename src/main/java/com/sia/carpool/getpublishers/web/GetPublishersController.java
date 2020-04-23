package com.sia.carpool.getpublishers.web;

import com.sia.carpool.getpublishers.GetPublishersInput;
import com.sia.carpool.getpublishers.GetPublishersResult;
import com.sia.carpool.getpublishers.GetPublishersService;
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
public class GetPublishersController {

    private ModelMapper modelMapper;
    private GetPublishersService getPublishersService;

    @PostMapping("/getpublishers")
    public GetPublishersResponse getPublishers(
            HttpServletRequest request,
            @RequestBody GetPublishersRequest publishersRequest,
            HttpServletResponse response) {
        GetPublishersInput publishersInput = modelMapper
                .map(publishersRequest, GetPublishersInput.class);

        GetPublishersResult publishersResult = getPublishersService
                .getPublishers(publishersInput);

        response.setHeader(
                CACHE_CONTROL,
                CacheControl.noStore().getHeaderValue()
        );

        return modelMapper.map(publishersResult, GetPublishersResponse.class);
    }
}
