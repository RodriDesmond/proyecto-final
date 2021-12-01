package com.informatorio.proyectofinal.controller;
import com.informatorio.proyectofinal.repository.EmprendimientoRepository;
import com.informatorio.proyectofinal.service.VoteService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emprendimientos")
public class EmprendimientoController {

    private final EmprendimientoRepository emprendimientoRepository;
    private final VoteService voteService;

    public EmprendimientoController(EmprendimientoRepository emprendimientoRepository, VoteService voteService) {
        this.emprendimientoRepository = emprendimientoRepository;
        this.voteService = voteService;
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
}
