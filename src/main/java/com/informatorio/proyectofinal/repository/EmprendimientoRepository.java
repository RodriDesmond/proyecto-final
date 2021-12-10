package com.informatorio.proyectofinal.repository;

import com.informatorio.proyectofinal.entity.Emprendimiento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmprendimientoRepository extends JpaRepository<Emprendimiento, Long> {

    List<Emprendimiento> findByCreatorId(Long id);
    @Query("SELECT e FROM Emprendimiento e join fetch e.tags t WHERE t.name LIKE %:tag%")
    List<Emprendimiento> findByTag(@Param("tag") String tag);
    List<Emprendimiento> findByPublished(boolean published);
}
