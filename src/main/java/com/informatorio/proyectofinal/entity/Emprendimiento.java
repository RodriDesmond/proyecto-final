package com.informatorio.proyectofinal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Emprendimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private String content;
    @CreationTimestamp
    private LocalDate created;
    private double goal;
    private boolean published;
    @ManyToOne(fetch = FetchType.LAZY)
    private User creator;
    @JsonIgnore
    @OneToMany(mappedBy = "emprendimiento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LineaDeEmprendimientos> lineaDeEmprendimientos = new ArrayList<>();
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Tags> tags = new ArrayList<>();
    @OneToMany(mappedBy = "emprendimientoId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Voto> voto = new ArrayList<>();

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public LocalDate getCreated() {
        return created;
    }
    public void setCreated(LocalDate created) {
        this.created = created;
    }
    public double getGoal() {
        return goal;
    }
    public void setGoal(double goal) {
        this.goal = goal;
    }
    public boolean isPublished() {
        return published;
    }
    public void setPublished(boolean published) {
        this.published = published;
    }
    public String getCreator() {
        return name = creator.getUsername();
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<LineaDeEmprendimientos> getLineaDeEmprendimientos(){
        return lineaDeEmprendimientos;
    }

    public void addLineaDeEmprendimiento(LineaDeEmprendimientos lineaDeEmprendimiento){
        lineaDeEmprendimientos.add(lineaDeEmprendimiento);
        lineaDeEmprendimiento.setEmprendimiento(this);
    }

    public void removeLineaDeEmprendimiento(LineaDeEmprendimientos lineaDeEmprendimiento){
        lineaDeEmprendimientos.remove(lineaDeEmprendimiento);
        lineaDeEmprendimiento.setEmprendimiento(null);
    }

    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }

    public void agregarTag(Tags tag) {
        tags.add(tag);
        tag.getEmprendimiento().add(this);
    }

    public void setLineaDeEmprendimientos(List<LineaDeEmprendimientos> lineaDeEmprendimientos) {
        this.lineaDeEmprendimientos = lineaDeEmprendimientos;
    }

    public List<Voto> getVoto() {
        return voto;
    }

    public void setVoto(List<Voto> voto) {
        this.voto = voto;
    }
}
