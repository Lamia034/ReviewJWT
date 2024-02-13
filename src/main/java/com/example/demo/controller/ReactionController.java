package com.example.demo.controller;

import com.example.demo.model.request.ReactionReqDto;
import com.example.demo.service.ReactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/reaction")
public class ReactionController {

    @Autowired
    ReactionService reactionService;

    @PostMapping("/")
    public String addReaction(@Valid @RequestBody ReactionReqDto reaction){
        reactionService.save(reaction);
        return "review";
    }

    @DeleteMapping("/{id}")
    public String deleteReaction(@PathVariable UUID id){
        reactionService.delete(id);
        return "review";
    }
}
