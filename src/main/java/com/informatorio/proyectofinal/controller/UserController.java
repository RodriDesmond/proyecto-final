package com.informatorio.proyectofinal.controller;

import com.informatorio.proyectofinal.entity.Emprendimiento;
import com.informatorio.proyectofinal.entity.User;
import com.informatorio.proyectofinal.repository.EmprendimientoRepository;
import com.informatorio.proyectofinal.repository.UserRepository;
import com.informatorio.proyectofinal.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    private final UserRepository userRepository;
    private final EmprendimientoRepository emprendimientoRepository;

    public UserController(UserRepository userRepository, EmprendimientoRepository emprendimientoRepository) {
        this.emprendimientoRepository = emprendimientoRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(
            @RequestParam(name = "createdDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime createdDate,
            @RequestParam(name = "username", required = false) String username,
            @RequestParam(name = "city", required = false) String city){
                if(createdDate != null){
                    return new ResponseEntity<>(userRepository.findByCreatedDateAfter(createdDate), HttpStatus.OK);
                } else if (Objects.nonNull(username)) {
                    return new ResponseEntity<>(userRepository.findByUsername(username), HttpStatus.OK);
                } else if (Objects.nonNull(city)){
                    return new ResponseEntity<>(userRepository.findByCity(city), HttpStatus.OK);
                }
                return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public User createUser(@Valid @RequestBody User user){
        return this.userService.save(user);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(userRepository.findById(id),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user){
        return this.userService.updateUser(id, user);
    }

    @DeleteMapping("{id}")
    public void removeUser(@PathVariable Long id){
        User user = userRepository.getById(id);
        this.userService.removeUser(id, user);
    }

    @PostMapping("{id}/emprendimientos")
    public ResponseEntity<?> createEmprendimiento(
            @PathVariable("id") Long userId,
            @Valid @RequestBody Emprendimiento emprendimiento) {
        User user = userRepository.getById(userId);
        emprendimiento.setCreator(user);
        return new ResponseEntity<>(emprendimientoRepository.save(emprendimiento), HttpStatus.CREATED);
    }

    @GetMapping("{id}/emprendimientos")
    public ResponseEntity<?> getUserEmprendimiento(
            @PathVariable("id") Long userId) {
        User user = userRepository.getById(userId);
        return new ResponseEntity<>(emprendimientoRepository.findByCreatorId(user.getId()), HttpStatus.OK);
    }

    @GetMapping("/my-user")
    @ResponseBody
    public String currentUserName(Authentication authentication) {
        return authentication.getPrincipal().toString();
    }


}
