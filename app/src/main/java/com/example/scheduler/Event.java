package com.example.scheduler;

/**
 * Private class to store each event which the user puts into their calendar
 */
public class Event {

    protected Date date;
    protected Time startTime;
    protected Time endTime;

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
     * @return Positive int if this event is earlier
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

    public String toString() {
        String rtn = date.toString()
                + "\n" + startTime.toString() + "-" + endTime.toString();
        return rtn;
    }
}