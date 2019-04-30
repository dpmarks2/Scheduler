package com.example.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Private class to store each event which the user puts into their calendar
 */
public class Event {

    private Date date;
    private Time startTime;
    private Time endTime;

    public Event(Date date, Time startTime, Time endTime) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Time getStartTime() {
        return startTime;
    }
    public Time getEndTime() {
        return endTime;
    }
    public Date getDate() {
        return date;
    }

    /**
     * Compares to another event to see which is earlier
     * @param other Another event
     * @return Amount of hours this event is earlier (if positive)
     */
    public double compareTo(Event other) {
        if (date.compareTo(other.date) != 0) {
            return date.compareTo(other.date);
        }

        if (startTime.compareTo(other.startTime) != 0) {
            return startTime.compareTo(other.startTime);
        }

        return 0;
    }
}