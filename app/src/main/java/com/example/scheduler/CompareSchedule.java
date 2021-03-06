package com.example.scheduler;

import java.util.List;
import java.util.ArrayList;

public class CompareSchedule {
    /**
     * Compare two calendars.
     */
    public static List<Event> syncCalendars(List<Event> first, List<Event> second) {
        if (first == null || second == null) {
            if (first == second) {
                return null;
            } else if (first == null) {
                return second;
            } else {
                return first;
            }
        }

        List<Event> synced = new ArrayList<>();
        List<Event> copyFirst = new ArrayList<>();
        List<Event> copySecond = new ArrayList<>();
        for (int i = 0; i < first.size(); i++) {
            copyFirst.add(first.get(i));
        }
        for (int j = 0; j < second.size(); j++) {
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

    /**
     * Finds free time from a synced list of two schedules
     * @param synced list of events that have been sorted by time already (see above method)
     * @return list of free time Events
     */
    public static List<Event> findFreeTime(final List<Event> synced) {
        if (synced == null) {
            return null;
        }
        List<Event> freeTime = new ArrayList<>();
        for (int i = 0; i < synced.size() - 1; i++) {
            Event firstEvent = synced.get(i);
            Event secondEvent = synced.get(i + 1);
            if (firstEvent.getDate().compareTo(secondEvent.getDate()) != 0) {
                Event freeEvent = new Event(firstEvent.getDate(), firstEvent.getEndTime(), new Time(24, 0));
                freeTime.add(freeEvent);
            } else if (firstEvent.getEndTime().compareTo(secondEvent.getStartTime()) > 0.0) {
                Event freeEvent = new Event(firstEvent.getDate(), firstEvent.getEndTime(), secondEvent.getStartTime());
                freeTime.add(freeEvent);
            }
        }
        return freeTime;
    }
}
