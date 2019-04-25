package com.example.scheduler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class AddEvent extends AppCompatActivity {

    private List<Event> calendar = new ArrayList<Event>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        findViewById(R.id.home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddEvent.this, MainActivity.class);

                String jsonCalendar = new Gson().toJson(calendar);
                intent.putExtra("calendar", jsonCalendar);

                startActivity(intent);
            }
        });

        findViewById(R.id.addEvent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int startHour = getNumberIn(R.id.startHour);
                    int startMinute = getNumberIn(R.id.startMinute);
                    Time startTime = new Time(startHour, startMinute);
                    int endHour = getNumberIn(R.id.endHour);
                    int endMinute = getNumberIn(R.id.endHour);
                    Time endTime = new Time(endHour, endMinute);
                    Date date = new Date(getNumberIn(R.id.eventMonth), getNumberIn(R.id.eventDay), getNumberIn(R.id.eventYear));
                    String name = getTextIn(R.id.eventName);

                    Event newEvent = new Event(name, date, startTime, endTime);

                    int putIndex = 0;

                    while (putIndex < calendar.size() && newEvent.compareTo(calendar.get(putIndex)) > 0) {
                        putIndex++;
                    }

                    calendar.add(putIndex, newEvent);

                } catch (Exception e) {
                    System.out.println("Invalid inputs");
                }

            }
        });
    }

    String getTextIn(final int editor) {
        return ((EditText) findViewById(editor)).getText().toString();
    }

    private int getNumberIn(final int editor) {
        return Integer.parseInt(getTextIn(editor));
    }


    /**
     * Private class to store each event which the user puts into their calendar
     */
    private class Event implements Comparable<Event> {
        private String name;
        private Date date;
        private Time startTime;
        private Time endTime;

        public Event(String name, Date date, Time startTime, Time endTime) {
            this.name = name;
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



    private class Date implements Comparable<Date> {
        private int day;
        private int month;
        private int year;

        public Date(int day, int month, int year) {
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
    }

    private class Time implements Comparable<Time> {
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

}
