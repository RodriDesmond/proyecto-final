package com.informatorio.proyectofinal.repository;
import com.informatorio.proyectofinal.entity.Emprendimiento;
import com.informatorio.proyectofinal.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}
