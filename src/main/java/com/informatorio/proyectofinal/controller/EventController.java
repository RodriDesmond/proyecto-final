package com.informatorio.proyectofinal.controller;

import com.informatorio.proyectofinal.entity.Event;
import com.informatorio.proyectofinal.entity.User;
import com.informatorio.proyectofinal.repository.EventRepository;
import com.informatorio.proyectofinal.service.EventService;
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

    @GetMapping("/{id}")
    public ResponseEntity<?> getEventById(@PathVariable Long id){
        return new ResponseEntity<>(eventRepository.findById(id), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable Long id, @RequestBody Event event){
        return this.eventService.updateEvent(id, event);
    }

    @DeleteMapping("{id}")
    public void removeEvent(@PathVariable Long id){
        Event event = eventRepository.getById(id);
        eventService.removeEvent(id, event);
    }

    @PostMapping
    public ResponseEntity<?> addEvent(@Valid @RequestBody Event event) {
        return new ResponseEntity<>(eventRepository.save(event), HttpStatus.CREATED);
    }

    @PutMapping("/update-status")
    public void updateStatus(){
        this.eventService.update();
    }

    @GetMapping("/{id}/ranking")
    public ResponseEntity<?> getEventRanking(
            @PathVariable Long id) {
        return new ResponseEntity<>(eventRepository.findById(id), HttpStatus.OK);
    }
}
