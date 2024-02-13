package com.example.demo.model.request;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class ReactionReqDto {

    @NotNull(message = "person should be not null")
    private UUID person_id;

    @NotNull(message = "review should be not null")
    private UUID review_id;
}
