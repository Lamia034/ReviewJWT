package com.example.demo.model.request;

import com.example.demo.model.enums.Role;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PersonReqDto {

    @NotBlank(message = "username should be not blank")
    private String username;

    @NotBlank(message = "password should be not blank")
    private String password;

    @NotBlank(message = "email should be not blank")
    private String email;

    private Role role;
}
