package com.sia.carpool.registeruser.web;


import com.sia.carpool.registeruser.RegisterUserInput;
import com.sia.carpool.registeruser.RegisterUserService;
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
@RequestMapping("/register")
@AllArgsConstructor
public class RegisterUserController {

    private ModelMapper modelMapper;
    private RegisterUserService registerUserService;

    @PostMapping("/user")
    public void getRegisterUsers(
            HttpServletRequest servletRequest,
            @RequestBody RegisterUserRequest registerUserRequest,
            HttpServletResponse response) {

        RegisterUserInput userInput = modelMapper.map(registerUserRequest,
                RegisterUserInput.class);

        registerUserService.register(userInput);

        response.setHeader(
                CACHE_CONTROL,
                CacheControl.noStore().getHeaderValue()
        );

        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}
