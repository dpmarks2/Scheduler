package com.example.scheduler;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.Result;
import com.google.zxing.integration.android.IntentIntegrator;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import static android.Manifest.permission.CAMERA;

public class ScanActivity extends AppCompatActivity {

    private static final int REQUEST_CAMERA = 1;
    private ZXingScannerView mScannerView;
    private String jsonCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        Intent intent = getIntent();
        jsonCalendar = intent.getStringExtra("calendar");

        IntentIntegrator integrator = new IntentIntegrator(ScanActivity.this); // `this` is the current Activity
        integrator.initiateScan();
    }


    // Get the results:
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = com.example.scheduler.IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                String cal = result.getContents();
                Intent intent = new Intent(ScanActivity.this, CompareCalendars.class);
                intent.putExtra("compareCalendar", cal);
                intent.putExtra("calendar", jsonCalendar);
                startActivity(intent);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
