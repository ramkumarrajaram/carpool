package com.sia.carpool.registeruser.web;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterUserRequest {

    private String userName;
    private String mobileNumber;
    private String token;
}
