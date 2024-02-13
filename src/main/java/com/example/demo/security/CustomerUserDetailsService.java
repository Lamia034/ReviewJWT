package com.example.demo.security;

import com.example.demo.model.entity.Person;
import com.example.demo.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
//@Slf4j
//@Service
@Component
@RequiredArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService {
//@Autowired
    private final PersonRepository personRepository ;



    @Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Person person = personRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

    return User.builder()
            .username(person.getUsername())
            .password(person.getPassword())
            .roles("USER")
            .build();
}

}