package com.informatorio.proyectofinal.repository;

import com.informatorio.proyectofinal.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

    List<Voto> findByUserId(Long userId);
}
