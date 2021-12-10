package com.informatorio.proyectofinal.dto;

public class RegisterToEventDto {

    private Long eventId;
    private Long subscriberId;

    public Long getEventId() {
        return eventId;
    }
    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
    public Long getSubscriberId() {
        return subscriberId;
    }
    public void setSubscriberId(Long subscriberId) {
        this.subscriberId = subscriberId;
    }
}
