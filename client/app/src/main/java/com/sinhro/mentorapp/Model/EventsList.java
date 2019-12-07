package com.sinhro.mentorapp.Model;

import java.io.Serializable;
import java.util.List;

public class EventsList implements Serializable {
    private List<FullEvent> events;

    public EventsList(List<FullEvent> events) {
        this.events = events;
    }

    public List<FullEvent> getEvents() {
        return events;
    }

    public void setEvents(List<FullEvent> events) {
        this.events = events;
    }
}
