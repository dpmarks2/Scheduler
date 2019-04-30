package com.example.scheduler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class AddEvent extends AppCompatActivity {

    private List<Event> calendar;

    private List<NamedEvent> namedCalendar = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        calendar = new ArrayList<>();

        Intent intent = getIntent();
        String jsonCalendar = intent.getStringExtra("calendar");
        String jsonNamedCalendar = intent.getStringExtra("namedCalendar");

        if (jsonCalendar != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Event>>(){}.getType();
            calendar = gson.fromJson(jsonCalendar, type);
        }
        if (jsonNamedCalendar != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<NamedEvent>>(){}.getType();
            namedCalendar = gson.fromJson(jsonNamedCalendar, type);
        }

        updateCalendar();

        findViewById(R.id.home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddEvent.this, MainActivity.class);

                String jsonCalendar = new Gson().toJson(calendar);
                String jsonNamedCalendar = new Gson().toJson(namedCalendar);
                intent.putExtra("calendar", jsonCalendar);
                intent.putExtra("namedCalendar", jsonNamedCalendar);

                startActivity(intent);
            }
        });

        findViewById(R.id.clearCalendar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = new ArrayList<>();
                namedCalendar = new ArrayList<>();
                updateCalendar();
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
                    int endMinute = getNumberIn(R.id.endMinute);
                    Time endTime = new Time(endHour, endMinute);
                    Date date = new Date(getNumberIn(R.id.eventMonth), getNumberIn(R.id.eventDay), getNumberIn(R.id.eventYear));
                    String name = getTextIn(R.id.eventName);

                    NamedEvent newNamedEvent = new NamedEvent(name, date, startTime, endTime);
                    Event newEvent = new Event(date, startTime, endTime);

                    int putIndex = 0;

                    while (putIndex < calendar.size() && newEvent.compareTo(calendar.get(putIndex)) < 0) {
                        putIndex++;
                    }
                    calendar.add(putIndex, newEvent);
                    namedCalendar.add(putIndex, newNamedEvent);

                    updateCalendar();

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

    private void updateCalendar() {
        String calendarPrint = "";

        for (Event event : namedCalendar) {
            calendarPrint += event.toString() + "\n\n";
        }

        ((TextView) findViewById(R.id.calendarView)).setText(calendarPrint);
    }

}