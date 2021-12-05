package com.informatorio.proyectofinal.service;


import com.informatorio.proyectofinal.dto.RegisterToEventDto;
import com.informatorio.proyectofinal.entity.Emprendimiento;
import com.informatorio.proyectofinal.entity.Event;
import com.informatorio.proyectofinal.entity.Status;
import com.informatorio.proyectofinal.repository.EmprendimientoRepository;
import com.informatorio.proyectofinal.repository.EventRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
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
    //@Scheduled(fixedRate = 1000)
    public void update() {
        List<Event> events = eventRepository.findAll();
        LocalDate now = LocalDate.now();
        events.stream()
                .forEach(event -> actualizarStatus(event, now));
        eventRepository.saveAll(events);
        System.out.println("Updated event statuses.");
    }

    private void actualizarStatus(Event event, LocalDate now) {
        if (event.getRegistrationClosure().isBefore(now)) {
            event.setStatus(Status.IN_COURSE);
        }
        if (event.getEndDate().isBefore(now)) {
            event.setStatus(Status.FINALIZED);
        }
    }

    public Event register(Long empId, Long eventId, RegisterToEventDto registerToEventDto) {
        Emprendimiento emprendimiento1 = emprendimientoRepository.getById(empId);
        Event event1 = eventRepository.getById(eventId);
        if(event1.getStatus() == Status.OPEN) {
            event1.addSubsribers(emprendimiento1);
            System.out.println("Se ha subscrito a este evento correctamente.");
        } else if (event1.getStatus() == Status.IN_COURSE){
            System.out.println("El tiempo de subscripcion a este evento a finalizado.");
        } else{
            System.out.println("Este evento a finalizado.");
        }
        return eventRepository.save(event1);
    }
}