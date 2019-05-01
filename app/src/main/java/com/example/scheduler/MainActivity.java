package com.example.scheduler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;

public class MainActivity extends AppCompatActivity {

    private TextView textViewName;
    private TextView textViewAddress;
    private TextView calendar;
    private Button scan;

    private IntentIntegrator qrScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getActionBar().hide();

        Intent intent = getIntent();

        String jsonCalendar = intent.getStringExtra("calendar");
        String compareCalendar = intent.getStringExtra("compareCalendar");
        String namedCalendar = intent.getStringExtra("namedCalendar");

        qrScan = new IntentIntegrator(this);

        findViewById(R.id.scan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScanActivity.class);
                intent.putExtra("calendar", jsonCalendar);
                intent.putExtra("namedCalendar", namedCalendar);
                startActivity(intent);
            }
        });

        findViewById(R.id.toCalendar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
                intent.putExtra("calendar", jsonCalendar);
                intent.putExtra("namedCalendar", namedCalendar);
                startActivity(intent);
            }
        });

        findViewById(R.id.qrGenerate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QRActivity.class);
                intent.putExtra("calendar", jsonCalendar);
                intent.putExtra("namedCalendar", namedCalendar);
                startActivity(intent);
            }
        });

    }
}