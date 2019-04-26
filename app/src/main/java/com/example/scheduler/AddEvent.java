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

}