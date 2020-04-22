package com.sia.carpool.registeruser;

import com.sia.carpool.CarPoolException;
import com.sia.carpool.registeruser.persistance.RegisterUserEntity;
import com.sia.carpool.registeruser.persistance.RegisterUserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegisterUserService {
    private ModelMapper modelMapper;
    private RegisterUserRepository registerUserRepository;

    public void register(RegisterUserInput userInput) {
        RegisterUserEntity userEntity = modelMapper.map(userInput, RegisterUserEntity.class);

        try {
            registerUserRepository.save(userEntity);
        } catch (Exception e) {
            throw new CarPoolException(e.getMessage());
        }
    }
}
