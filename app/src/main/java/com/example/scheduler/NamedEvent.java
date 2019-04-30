package com.example.scheduler;

public class NamedEvent extends Event {
    private String name;

    public NamedEvent(String name, Date date, Time startTime, Time endTime) {
        super(date, startTime, endTime);
        this.name = name;
    }

    public String toString() {
        String rtn = name + " " + date.toString()
                + "\n" + startTime.toString() + "-" + endTime.toString();
        return rtn;
    }
}
