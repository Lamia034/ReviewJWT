package com.example.demo.model.response;

import com.example.demo.model.request.PersonReqDto;
import com.example.demo.model.request.ReviewReqDto;
import lombok.Data;

import java.util.UUID;

@Data
public class ReactionResDto {

    private UUID id;

    private PersonReqDto person;

    private ReviewReqDto review;
}
