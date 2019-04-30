package com.example.scheduler;


public class Time {
    private int hour;
    private int minute;


    public Time(int setHour, int setMinute) {
        this.hour = setHour;
        this.minute = setMinute;
    }

    /**
     * returns positive if other is after the first Time value.
     * @param other other time we are comparing to
     * @return positive if other is after, negative if other is before
     */
    public double compareTo(Time other) {
        if (minute == other.minute) {
            return (other.hour - hour);
        } else {
            double theDub = (other.minute - minute) / 60;
            return (other.hour - hour) + theDub;
        }
    }
}
