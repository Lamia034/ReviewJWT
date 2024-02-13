package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.entity.Reaction;
import com.example.demo.model.request.ReactionReqDto;
import com.example.demo.model.response.ReactionResDto;
import com.example.demo.repository.ReactionRepository;
import com.example.demo.service.ReactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReactionServiceImpl implements ReactionService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ReactionRepository reactionRepository;
    @Override
    public ReactionResDto save(ReactionReqDto reactionReqDto) {
        return modelMapper.map(reactionRepository.save(modelMapper.map(reactionReqDto, Reaction.class)), ReactionResDto.class);
    }

    @Override
    public List<ReactionResDto> findAll() {
        return reactionRepository.findAll().stream().map(reaction -> modelMapper.map(reaction, ReactionResDto.class)).collect(Collectors.toList());
    }

    @Override
    public ReactionResDto findById(UUID id) {
        return reactionRepository.findById(id)
                .map(reaction -> modelMapper.map(reaction, ReactionResDto.class)).orElseThrow(() -> new ResourceNotFoundException("Reaction Not found with this: " + id));
    }

    @Override
    public ReactionResDto update(UUID id, ReactionReqDto reactionReqDto) {
        Reaction existingReaction = reactionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Reaction Not found with this: " + id));
        BeanUtils.copyProperties(reactionReqDto, existingReaction);
        existingReaction.setId(id);
        return modelMapper.map(reactionRepository.save(existingReaction), ReactionResDto.class);
    }

    @Override
    public void delete(UUID id) {
        reactionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Reaction Not found with this: " + id));
        reactionRepository.deleteById(id);
    }
}
