package com.example.demo.service;

import com.example.demo.model.request.PersonReqDto;
import com.example.demo.model.response.PersonResDto;

import java.util.UUID;

public interface PersonService extends BaseService<PersonReqDto, UUID, PersonResDto>{
}
