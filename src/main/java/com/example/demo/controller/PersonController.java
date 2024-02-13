package com.example.demo.controller;

import com.example.demo.model.enums.Role;
import com.example.demo.model.request.PersonReqDto;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("/save")
    public String addUser(){

        PersonReqDto user = new PersonReqDto();
        user.setUsername("lamia");
        user.setEmail("Test@test.test");
        user.setPassword("$2a$12$MWcWZfe9J7X8RjYy62iQDuFIt3F7.TwUyoQccMzyFay0ENUMaaPt2");
        user.setRole(Role.ROLE_USER);
        personService.save(user);

        return "home";
    }
}
