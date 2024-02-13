package com.example.demo.service.impl;

import com.example.demo.model.entity.Person;
import com.example.demo.model.request.RegistrationRequest;
import com.example.demo.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(RegistrationRequest registrationRequest) {

        String encodedPassword = passwordEncoder.encode(registrationRequest.getPassword());

        Person newPerson = new Person();
        newPerson.setUsername(registrationRequest.getUsername());
        newPerson.setPassword(encodedPassword);
        // Set other user details as needed

        personRepository.save(newPerson);
    }
}

