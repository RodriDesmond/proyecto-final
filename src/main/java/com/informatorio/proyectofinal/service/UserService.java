package com.informatorio.proyectofinal.service;
import com.informatorio.proyectofinal.entity.User;
import com.informatorio.proyectofinal.repository.UserRepository;

import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private static List<User> list = new ArrayList<>();
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
        if(user.getFirstname() != null){
            inDB.setFirstname(user.getFirstname());
        }
        if(user.getLastname() != null){
            inDB.setLastname(user.getLastname());
        }
        if(user.getCity() != null) {
            inDB.setCity(user.getCity());
        }
        if(user.getProvince() != null) {
            inDB.setProvince(user.getProvince());
        }
        if(user.getCountry() != null) {
            inDB.setCountry(user.getCountry());
        }
        if(user.getRole() != null) {
            inDB.setRole(user.getRole());
        }

        inDB.setLastUpdated(LocalDateTime.now());
        return userRepository.save(inDB);
    }

    public User removeUser(Long id, User user) {
        User inDB = userRepository.getById(id);
        userRepository.delete(inDB);
        return userRepository.save(inDB);
    }
}
