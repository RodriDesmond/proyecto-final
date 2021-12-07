package com.informatorio.proyectofinal.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String name;
    private String details;
    @CreationTimestamp
    private LocalDate created;
    private LocalDate registrationClosure;
    private LocalDate endDate;
    @Column(name="status", nullable = false, columnDefinition = "varchar(32) default 'OPEN'")
    @Enumerated(value = EnumType.STRING)
    private Status status = Status.OPEN;
    @ManyToMany(mappedBy = "events")
    @JsonIgnoreProperties({"description","content","created","goal","published","tags" })
    @OrderBy("votesCount DESC")
    private List<Emprendimiento> emprendimientos;
    private Double winnerReward;

    public Event() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public LocalDate getRegistrationClosure() {
        return registrationClosure;
    }

    public void setRegistrationClosure(LocalDate registrationClosure) {
        this.registrationClosure = registrationClosure;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Status getStatus() {return status;}

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Emprendimiento> getEmprendimientos() {
        return emprendimientos;
    }

    public void setEmprendimientos(List<Emprendimiento> emprendimientos) {
        this.emprendimientos = emprendimientos;
    }

    public Double getWinnerReward() {
        return winnerReward;
    }

    public void setWinnerReward(Double winnerReward) {
        this.winnerReward = winnerReward;
    }

}


