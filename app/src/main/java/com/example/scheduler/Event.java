package com.example.scheduler;

/**
 * Private class to store each event which the user puts into their calendar
 */
public class Event implements Comparable<Event> {

    private Date date;
    private Time startTime;
    private Time endTime;

    public Event(Date date, Time startTime, Time endTime) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Compares to another event to see which is earlier
     * @param other Another event
     * @return Positive int if this event is earlier
     */
    @Override
    public int compareTo(Event other) {
        if (date.compareTo(other.date) != 0) {
            return date.compareTo(other.date);
        }

        if (startTime.compareTo(other.startTime) != 0) {
            return startTime.compareTo(other.startTime);
        }

        return 0;
    }
}