package com.informatorio.proyectofinal.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Tags> tags = new ArrayList<>();
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "emprendimientoId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Voto> votes = new ArrayList<>();
    private Integer votesCount = 0;
    @JoinTable(
            name = "events_emprendimientos",
            joinColumns = {@JoinColumn(name = "fk_emprendimiento",nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "fk_event",nullable = false)}
    )
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Event> events;


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
        return creator.getUsername();
    }

    public void setCreator(User creator) {
        this.creator = creator;
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


    public void addEvent(Event event){
        if(this.events == null){
            this.events = new ArrayList<>();
        }
        this.events.add(event);
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public int getVotes() {

        return votes.size();
    }

    public void setVotes(List<Voto> votes) {
        this.votes = votes;
    }

    public Integer getVotesCount() {
        return votesCount;
    }

    public void setVotesCount(Integer votesCount) {
        this.votesCount = votesCount;
    }
}
