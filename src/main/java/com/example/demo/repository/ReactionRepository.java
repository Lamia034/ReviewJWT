package com.example.demo.repository;

import com.example.demo.model.entity.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ReactionRepository extends JpaRepository<Reaction, UUID> {
}
