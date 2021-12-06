package com.informatorio.proyectofinal.repository;
import com.informatorio.proyectofinal.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}
