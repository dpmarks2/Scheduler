package com.example.scheduler;

public class Time implements Comparable<Time> {
    private int hour;
    private int minute;

    public Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    @Override
    public int compareTo(Time other) {
        if (hour != other.hour) {
            return other.hour - hour;
        }

        if (minute != other.minute) {
            return other.minute - minute;
        }

        return 0;
    }
}
