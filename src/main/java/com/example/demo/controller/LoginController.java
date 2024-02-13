package com.example.demo.controller;

import com.example.demo.model.request.LoginRequest;
import com.example.demo.service.impl.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {
    private final AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest) {
        String token = authenticationService.authenticateUser(loginRequest);
        System.out.println("token:"+ token);
        return ResponseEntity.ok(token);
    }
}
