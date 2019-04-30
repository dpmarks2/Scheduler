package com.example.scheduler;

import java.util.ArrayList;
import java.util.List;

public class ScheduleTest {
    public static void main(String[] args) {
        Event testEvent = new Event(new Date(3, 2, 10), new Time(2, 00), new Time(3, 00));

        Event testEvent2 = new Event(new Date(3, 2, 10), new Time(1,00), new Time(1, 30));

        List<Event> calendar = new ArrayList<>();
        calendar.add(testEvent);
        calendar.add(testEvent2);

        System.out.println(CompareSchedule.findFreeTime(calendar));
    }
}
