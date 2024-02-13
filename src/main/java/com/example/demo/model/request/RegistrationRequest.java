package com.example.demo.model.request;

import lombok.*;

import java.util.UUID;

@Data
public class RegistrationRequest {

    private UUID id;
    private String username;
    private String email;
    private String password;

}
