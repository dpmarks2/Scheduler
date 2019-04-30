package com.example.scheduler;

import java.util.List;
import java.util.ArrayList;

public class CompareSchedule {
    /**
     * Compare two calendars.
     */
    public List<Event> syncCalendars(List<Event> first, List<Event> second) {
        List<Event> synced = new ArrayList<Event>();
        List<Event> copyFirst = new ArrayList<Event>();
        List<Event> copySecond = new ArrayList<Event>();
        for (int i = 0; i < first.size(); i++) {
            copyFirst.add(first.get(i));
        }
        for (int j = 0; j < first.size(); j++) {
            copySecond.add(second.get(j));
        }
        while (copyFirst.size() != 0 || copySecond.size()!= 0) {
            if (copyFirst.size() == 0) {
                synced.add(copySecond.remove(0));
            } else if (copySecond.size() == 0) {
                synced.add(copyFirst.remove(0));
            } else if (copyFirst.get(0).compareTo(copySecond.get(0)) > 0) {
                synced.add(copyFirst.remove(0));
            } else {
                synced.add(copySecond.remove(0));
            }
        }
        return synced;
    }
    public static List<Event> findFreeTime(final List<Event> synced) {
        List<Event> freeTime = new ArrayList<Event>();
        for (int i = 0; i < synced.size() - 1; i++) {
            if (synced)
            if (synced.get(i).getEndTime().compareTo(synced.get(i + 1).getStartTime()) != 0.0) {
                Event freeEvent = new Event()
            }
        }
    }
}
