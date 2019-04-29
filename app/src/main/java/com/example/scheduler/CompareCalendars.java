package com.example.scheduler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class CompareCalendars extends AppCompatActivity {

    private String myCalendar;
    private List<Event> myList;
    private String compareCalendar;
    private List<Event> otherList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_calendars);

        Intent intent = getIntent();
        myCalendar = intent.getStringExtra("calendar");
        compareCalendar = intent.getStringExtra("compareCalendar");

        Gson gson = new Gson();
        Type type = new TypeToken<List<Event>>(){}.getType();
        if (myCalendar != null) {
            myList = gson.fromJson(myCalendar, type);
        }
        if (compareCalendar != null) {
            otherList = gson.fromJson(compareCalendar, type);
        }

        updateCalendar();


        findViewById(R.id.compareHome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompareCalendars.this, MainActivity.class);
                intent.putExtra("calendar", myCalendar);
                startActivity(intent);
            }
        });
    }

    private void updateCalendar() {
        if (myList != null && myList.size() != 0) {
            String myCalendarPrint = "";

            for (Event event : myList) {
                myCalendarPrint += event.toString() + "\n";
            }

            ((TextView) findViewById(R.id.myCalendar)).setText(myCalendarPrint);
        } else {
            ((TextView) findViewById(R.id.myCalendar)).setText("No Calendar");
        }


        if (otherList != null && otherList.size() != 0) {
            String otherCalendarPrint = "";

            for (Event event : otherList) {
                otherCalendarPrint += event.toString() + "\n";
            }

            ((TextView) findViewById(R.id.otherCalendar)).setText(otherCalendarPrint);
        } else {
            ((TextView) findViewById(R.id.otherCalendar)).setText("No Calendar");
        }

    }
}
