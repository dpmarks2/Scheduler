package com.example.scheduler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CalendarActivity extends AppCompatActivity {

    TextView calendarView;
    List<NamedEvent> namedCalendar;
    List<Event> calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendarView = (TextView) findViewById(R.id.scrollCalendar);

        Intent intent = getIntent();
        String jsonNamedCalendar = intent.getStringExtra("namedCalendar");
        String jsonCalendar = intent.getStringExtra("calendar");

        if (jsonCalendar != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Event>>(){}.getType();
            calendar = gson.fromJson(jsonCalendar, type);
        } else {
            calendar = new ArrayList<>();
        }
        if (jsonNamedCalendar != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<NamedEvent>>(){}.getType();
            namedCalendar = gson.fromJson(jsonNamedCalendar, type);
        } else {
            namedCalendar = new ArrayList<>();
        }



        updateCalendar();

        findViewById(R.id.addToCalendar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalendarActivity.this, AddEvent.class);

                String jsonCalendar = new Gson().toJson(calendar);
                String jsonNamedCalendar = new Gson().toJson(namedCalendar);

                intent.putExtra("calendar", jsonCalendar);
                intent.putExtra("namedCalendar", jsonNamedCalendar);

                startActivity(intent);
            }
        });

        findViewById(R.id.calendarToHome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalendarActivity.this, MainActivity.class);

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
    }

    private void updateCalendar() {
        String calendarPrint = "";

        for (Event event : namedCalendar) {
            calendarPrint += event.toString() + "\n\n";
        }

        ((TextView) findViewById(R.id.scrollCalendar)).setText(calendarPrint);
    }
}
