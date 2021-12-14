package com.informatorio.proyectofinal.controller;

import com.informatorio.proyectofinal.dto.EmprendimientoDto;
import com.informatorio.proyectofinal.dto.RegisterToEventDto;
import com.informatorio.proyectofinal.entity.Emprendimiento;
import com.informatorio.proyectofinal.entity.Event;
import com.informatorio.proyectofinal.repository.EmprendimientoRepository;
import com.informatorio.proyectofinal.repository.EventRepository;
import com.informatorio.proyectofinal.service.EmprendimientoService;
import com.informatorio.proyectofinal.service.EventService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/emprendimientos")
public class EmprendimientoController {

    private final EmprendimientoRepository emprendimientoRepository;
    private final EmprendimientoService emprendimientoService;
    private final EventService eventService;
    private final EventRepository eventRepository;

    public EmprendimientoController(EmprendimientoRepository emprendimientoRepository,
                                    EventService eventService,
                                    EventRepository eventRepository,
                                    EmprendimientoService emprendimientoService) {
        this.emprendimientoRepository = emprendimientoRepository;
        this.eventRepository = eventRepository;
        this.eventService = eventService;
        this.emprendimientoService = emprendimientoService;
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
        return new ResponseEntity<>(emprendimientoRepository.findById(id)
                .stream()
                .filter(Emprendimiento::isActive), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public Emprendimiento updateEmprendimiento(@PathVariable Long id, @RequestBody EmprendimientoDto emprendimientoDto){
        return this.emprendimientoService.updateEmprendimiento(id, emprendimientoDto);
    }

    @DeleteMapping("{id}")
    public void removeEmprendimiento(@PathVariable Long id){
        Emprendimiento emprendimiento = emprendimientoRepository.getById(id);
        emprendimientoService.removeEmprendimiento(id, emprendimiento);
    }

    @PostMapping("{empId}/events/{eventId}")
    public ResponseEntity<?> register(@PathVariable("empId") Long empId,
                                      @PathVariable("eventId") Long eventId, RegisterToEventDto registerToEventDto) {
        emprendimientoRepository.findById(empId);
        eventRepository.findById(eventId);
        return new ResponseEntity<>(eventService.register(empId, eventId, registerToEventDto), HttpStatus.CREATED);
    }
}
