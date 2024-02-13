package com.example.demo.model.response;

import com.example.demo.model.entity.Reaction;
import com.example.demo.model.enums.Role;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class PersonResDto {

    private UUID id;

    private String username;

    private String password;

    private String email;

    private Role role;

    private List<Reaction> reactions;
}
