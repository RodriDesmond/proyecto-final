package com.informatorio.proyectofinal.controller;

import com.informatorio.proyectofinal.dto.VoteDTO;
import com.informatorio.proyectofinal.entity.Event;
import com.informatorio.proyectofinal.entity.User;
import com.informatorio.proyectofinal.repository.EmprendimientoRepository;
import com.informatorio.proyectofinal.repository.EventRepository;
import com.informatorio.proyectofinal.service.EventService;
import com.informatorio.proyectofinal.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private final EventService eventService;

    private final EventRepository eventRepository;


    public EventController(EventRepository eventRepository, EventService eventService) {
        this.eventRepository = eventRepository;
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<?> getAllEvents(){
        return new ResponseEntity<>(eventRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addEvent(@Valid @RequestBody Event event) {
        return new ResponseEntity<>(eventRepository.save(event), HttpStatus.CREATED);
    }

    @PutMapping("/update-status")
    public void updateStatus(){
        this.eventService.update();
    }
}