package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;
import java.util.UUID;
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class Review extends Auditable{

    @Id
    @GeneratedValue
    private UUID id;

    private String title;

    private String message;

    @OneToMany(mappedBy = "review",fetch = FetchType.LAZY)
    private List<Reaction> reactions;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Person person;
}
