package com.example.scheduler;

public class NamedEvent extends Event {
    private String name;

    public NamedEvent(String name, Date date, Time startTime, Time endTime) {
        super(date, startTime, endTime);
        this.name = name;
    }
}
