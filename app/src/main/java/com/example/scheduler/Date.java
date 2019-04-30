package com.example.scheduler;

public class Date implements Comparable<Date> {
    private int day;
    private int month;
    private int year;

    public Date(int month, int day, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * Compares date to another date
     * @param other Another date
     * @return Int greater than 0 if this event is earlier
     */
    @Override
    public int compareTo(Date other) {
        if (year != other.year) {
            return other.year - year;
        }

        if (month != other.month) {
            return other.month - month;
        }

        if (day != other.day) {
            return other.day - day;
        }

        return 0;
    }

    public String toString() {
        return month + "/" + day + "/" + year;
    }
}