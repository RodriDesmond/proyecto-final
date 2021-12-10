package com.informatorio.proyectofinal.repository;

import com.informatorio.proyectofinal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User  findByUsername(String username);
    List<User> findByCreatedDateAfter(LocalDateTime createdDate);
    List<User> findByCity(String city);
}
