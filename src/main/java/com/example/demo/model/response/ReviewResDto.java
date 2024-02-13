package com.example.demo.model.response;

import com.example.demo.model.entity.Reaction;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ReviewResDto {

    private UUID id;

    private String title;

    private String message;

    private List<Reaction> reactions;
}
