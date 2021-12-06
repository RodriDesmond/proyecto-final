package com.informatorio.proyectofinal.controller;

import com.informatorio.proyectofinal.dto.VoteDTO;
import com.informatorio.proyectofinal.repository.VotoRepository;
import com.informatorio.proyectofinal.service.VoteService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class VotoController {

    private final VoteService voteService;
    private final VotoRepository votoRepository;

    public VotoController(VoteService voteService, VotoRepository votoRepository) {
        this.voteService = voteService;
        this.votoRepository = votoRepository;
    }

    @PostMapping
    @RequestMapping("/vote")
    public ResponseEntity<?> votar(@Valid @RequestBody VoteDTO voteDTO) {
        return new ResponseEntity<>(voteService.createVote(voteDTO), HttpStatus.CREATED);
    }

    @GetMapping
    @RequestMapping("votes/{userId}")
    public ResponseEntity<?> getVotesFromAUser(
            @PathVariable("userId") Long userId) {
                return new ResponseEntity<>(votoRepository.findByUserId(userId), HttpStatus.OK);
    }

}
