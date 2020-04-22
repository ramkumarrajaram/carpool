package com.sia.carpool.registeruser.persistance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "register_user")
public class RegisterUserEntity {

    @Id
    private String mobileNumber;

    private String userName;

    private String token;
}
