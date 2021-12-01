package com.informatorio.proyectofinal.controller;

import com.informatorio.proyectofinal.dto.VoteDTO;
import com.informatorio.proyectofinal.service.VoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class VotoController {

    private final VoteService voteService;
    public VotoController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping
    @RequestMapping("/vote")
    public ResponseEntity<?> votar(@Valid @RequestBody VoteDTO voteDTO) {
        return new ResponseEntity<>(voteService.createVote(voteDTO), HttpStatus.CREATED);
    }
}
