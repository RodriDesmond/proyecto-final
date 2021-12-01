package com.informatorio.proyectofinal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "emprendimientos_tags")
public class Tags {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "tags")
    private List<Emprendimiento> emprendimiento = new ArrayList<>();

    public Tags(){}

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

    public List<Emprendimiento> getEmprendimiento() {
        return emprendimiento;
    }

    public void setEmprendimiento(List<Emprendimiento> emprendimiento) {
        this.emprendimiento = emprendimiento;
    }
}
