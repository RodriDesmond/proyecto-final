package com.informatorio.proyectofinal.service;


import com.informatorio.proyectofinal.dto.RegisterToEventDto;
import com.informatorio.proyectofinal.entity.Emprendimiento;
import com.informatorio.proyectofinal.entity.Event;
import com.informatorio.proyectofinal.entity.Status;
import com.informatorio.proyectofinal.entity.User;
import com.informatorio.proyectofinal.repository.EmprendimientoRepository;
import com.informatorio.proyectofinal.repository.EventRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Configuration
@EnableScheduling
public class EventService {

    private final EventRepository eventRepository;
    private final EmprendimientoRepository emprendimientoRepository;;

    public EventService(EventRepository eventRepository, EmprendimientoRepository emprendimientoRepository) {
        this.eventRepository = eventRepository;
        this.emprendimientoRepository = emprendimientoRepository;
    }

    //@Scheduled(cron = "0 6 * * * ?")
    public void update() {
        List<Event> events = eventRepository.findAll();
        LocalDate now = LocalDate.now();
        events.stream()
                .forEach(event -> actualizarStatus(event, now));
        eventRepository.saveAll(events);
        System.out.println("Updated event statuses.");
    }

    public Event updateEvent(Long id, Event event) {
        Event inDB = eventRepository.getById(id);
        if (event.getName() != null) {
            inDB.setName(event.getName());
        }
        if (event.getDetails() != null) {
            inDB.setDetails(event.getDetails());
        }
        if (event.getWinnerReward() != null) {
            inDB.setWinnerReward(event.getWinnerReward());
        }
        if (event.getEndDate() != null) {
            inDB.setEndDate(event.getEndDate());
        }
        if (event.getStatus() != null) {
            inDB.setStatus(event.getStatus());
        }
        if (event.getRegistrationClosure() != null) {
            inDB.setRegistrationClosure(event.getRegistrationClosure());
        }
        return eventRepository.save(inDB);
    }

    private void actualizarStatus(Event event, LocalDate now) {
        if (event.getRegistrationClosure().isBefore(now)) {
            event.setStatus(Status.IN_COURSE);
        }
        if (event.getEndDate().isBefore(now)) {
            event.setStatus(Status.FINALIZED);
        }

    }

    public Emprendimiento register(Long empId, Long eventId, RegisterToEventDto registerToEventDto) {
        Emprendimiento emprendimiento1 = emprendimientoRepository.getById(empId);
        Event event1 = eventRepository.getById(eventId);
        if(event1.getStatus() == Status.OPEN) {
            emprendimiento1.addEvent(event1);
            System.out.println("Se ha subscrito a este evento correctamente.");
        } else if (event1.getStatus() == Status.IN_COURSE){
            System.out.println("El tiempo de subscripcion a este evento a finalizado.");
        } else{
            System.out.println("Este evento a finalizado.");
        }
        return emprendimientoRepository.save(emprendimiento1);
    }

    public Event removeEvent(Long id, Event event) {
        Event inDB = eventRepository.getById(id);
        inDB.setActive(false);
        return eventRepository.save(inDB);
    }
}