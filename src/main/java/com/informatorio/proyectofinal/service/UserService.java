package com.informatorio.proyectofinal.service;
import com.informatorio.proyectofinal.entity.User;
import com.informatorio.proyectofinal.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User save(User user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

    public User updateUser(Long id, User user) {
        User inDB = userRepository.getById(id);
        inDB.setLastUpdated(LocalDateTime.now());
        return userRepository.save(inDB);
    }

    public User removeUser(Long id, User user) {
        User inDB = userRepository.getById(id);
        inDB.setActive(false);
        return userRepository.save(inDB);
    }
}
