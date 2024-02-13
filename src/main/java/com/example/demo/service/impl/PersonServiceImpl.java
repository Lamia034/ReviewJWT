package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.entity.Person;
import com.example.demo.model.request.PersonReqDto;
import com.example.demo.model.response.PersonResDto;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PersonRepository personRepository;
    @Override
    public PersonResDto save(PersonReqDto personReqDto) {
        return modelMapper.map(personRepository.save(modelMapper.map(personReqDto, Person.class)), PersonResDto.class);
    }

    @Override
    public List<PersonResDto> findAll() {
        return personRepository.findAll().stream().map(person -> modelMapper.map(person, PersonResDto.class)).collect(Collectors.toList());
    }

    @Override
    public PersonResDto findById(UUID id) {
        return personRepository.findById(id)
                .map(person -> modelMapper.map(person, PersonResDto.class)).orElseThrow(() -> new ResourceNotFoundException("Person Not found with this: " + id));
    }

    @Override
    public PersonResDto update(UUID id, PersonReqDto personReqDto) {
        Person existingPerson = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person Not found with this: " + id));
        BeanUtils.copyProperties(personReqDto, existingPerson);
        existingPerson.setId(id);
        return modelMapper.map(personRepository.save(existingPerson), PersonResDto.class);
    }

    @Override
    public void delete(UUID id) {
        personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person Not found with this: " + id));
        personRepository.deleteById(id);
    }
}
