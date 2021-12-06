package com.informatorio.proyectofinal.controller;
import com.informatorio.proyectofinal.dto.RegisterToEventDto;
import com.informatorio.proyectofinal.repository.EmprendimientoRepository;
import com.informatorio.proyectofinal.repository.EventRepository;
import com.informatorio.proyectofinal.service.EventService;
import com.informatorio.proyectofinal.service.VoteService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/emprendimientos")
public class EmprendimientoController {

    private final EmprendimientoRepository emprendimientoRepository;
    private final VoteService voteService;
    private final EventService eventService;
    private final EventRepository eventRepository;

    public EmprendimientoController(EmprendimientoRepository emprendimientoRepository, VoteService voteService, EventService eventService, EventRepository eventRepository) {
        this.emprendimientoRepository = emprendimientoRepository;
        this.eventRepository = eventRepository;
        this.voteService = voteService;
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<?> getAllEmprendimientos(
            @RequestParam(name = "tag", required = false) String tag,
            @RequestParam(name = "published", required = false, defaultValue = "true") boolean published) {
        if(tag != null){
            return new ResponseEntity<>(emprendimientoRepository.findByTag(tag), HttpStatus.OK);
        } else if (!published) {
            return new ResponseEntity<>(emprendimientoRepository.findByPublished(false), HttpStatus.OK);
        }
        return new ResponseEntity<>(emprendimientoRepository.findByPublished(true), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmprendimientoById(
            @PathVariable("id") Long id) {
        return new ResponseEntity<>(emprendimientoRepository.findById(id), HttpStatus.OK);
    }

    @PutMapping("{empId}/events/{eventId}")
    public ResponseEntity<?> register(@PathVariable("empId") Long empId,
                                      @PathVariable("eventId") Long eventId, RegisterToEventDto registerToEventDto) {
        emprendimientoRepository.findById(empId);
        eventRepository.findById(eventId);
        return new ResponseEntity<>(eventService.register(empId, eventId, registerToEventDto), HttpStatus.CREATED);
    }
}
