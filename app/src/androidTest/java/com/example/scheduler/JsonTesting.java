package com.example.scheduler;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class JsonTesting {
    public static void main(String[] args) {

        Event testEvent = new Event(new Date(3, 2, 10), new Time(2, 00), new Time(3, 00));

        List<Event> calendar = new ArrayList<>();
        calendar.add(testEvent);

        String jsonCalendar = new Gson().toJson(calendar);
        System.out.println(jsonCalendar);

    }
}
