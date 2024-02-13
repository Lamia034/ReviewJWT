package com.example.demo.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewReqDto {

    @NotBlank(message = "title should be not blank")
    private String title;

    @NotBlank(message = "message should be not blank")
    private String message;
    private LocalDateTime lastModifiedDate;
}
