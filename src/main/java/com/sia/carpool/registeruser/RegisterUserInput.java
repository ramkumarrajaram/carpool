package com.sia.carpool.registeruser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserInput {

    private String userName;
    private String mobileNumber;
    private String token;
}
