package com.informatorio.proyectofinal.repository;

import com.informatorio.proyectofinal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User  findByUsername(String username);

    List<User> findByCreatedDateAfter(LocalDateTime createdDate);

    @Transactional
    void deleteByUsername(String username);
}
