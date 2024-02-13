package com.example.demo.controller;

import com.example.demo.model.request.RegistrationRequest;
import com.example.demo.service.impl.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;
//    @GetMapping
//    public String showRegistrationForm() {
//        return "register";
//    }
    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody RegistrationRequest registrationRequest) {
        registrationService.registerUser(registrationRequest);
        return ResponseEntity.ok("User registered successfully");
    }
}
