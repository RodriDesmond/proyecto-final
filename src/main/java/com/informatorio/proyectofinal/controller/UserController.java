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
import java.time.LocalDate;
import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user){
        return this.userService.save(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user){
        return this.userService.updateUser(id, user);
    }

    @PutMapping("{id}/remove")
    public User removeUser(@PathVariable Long id, User user){
        return this.userService.removeUser(id, user);
    }

    private final UserRepository userRepository;
    private final EmprendimientoRepository emprendimientoRepository;

    public UserController(UserRepository userRepository, EmprendimientoRepository emprendimientoRepository) {
        this.emprendimientoRepository = emprendimientoRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getUser(@PathVariable long id) {
        return new ResponseEntity<>(userRepository.findById(id),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(
            @RequestParam(name = "created", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate createdDate,
            @RequestParam(name = "username", required = false) String username,
            @RequestParam(name = "active", required = false) boolean active){
                if(createdDate != null){
                    return new ResponseEntity<>(userRepository.findByCreatedDateAfter(createdDate.atStartOfDay()), HttpStatus.OK);
                } else if (Objects.nonNull(username)) {
                    return new ResponseEntity<>(userRepository.findByUsername(username), HttpStatus.OK);
                }
                return new ResponseEntity<>(userRepository.findByActive(true), HttpStatus.OK);
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
        return authentication.getName();
    }
}
