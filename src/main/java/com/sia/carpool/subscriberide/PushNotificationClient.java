package com.sia.carpool.subscriberide;

import com.sia.carpool.subscriberide.fcm.PushNotificationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(name= "pushNotification", url = "https://fcm.googleapis.com")
public interface PushNotificationClient {

    @PostMapping(path = "/fcm/send", consumes = APPLICATION_JSON_VALUE)
    void pushNotification(
            @RequestHeader("Authorization") String token,
            @RequestBody PushNotificationRequest request);
}