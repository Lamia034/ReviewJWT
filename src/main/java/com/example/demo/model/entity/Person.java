package com.example.demo.model.entity;

import com.example.demo.model.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public final class Person extends Auditable{

    @Id
    @GeneratedValue
    private UUID id;

    private String username;

    private String password;

    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "person",fetch = FetchType.LAZY)
    private List<Reaction> reactions;

    @OneToMany(mappedBy = "person",fetch = FetchType.LAZY)
    private List<Review> reviews;

}
