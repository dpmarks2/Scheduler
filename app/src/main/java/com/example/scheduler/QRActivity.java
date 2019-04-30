package com.example.scheduler;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class QRActivity extends AppCompatActivity {

    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        Intent intent = getIntent();
        String qrJson = intent.getStringExtra("calendar");
        String namedCalendar = intent.getStringExtra("namedCalendar");

        ImageView qr = (ImageView) findViewById(R.id.qrCode);

        QRGEncoder qrgEncoder = new QRGEncoder(qrJson, null, QRGContents.Type.TEXT, 400);

        try {
            // Getting QR-Code as Bitmap
            bitmap = qrgEncoder.encodeAsBitmap();
            // Setting Bitmap to ImageView
            qr.setImageBitmap(bitmap);
        } catch (WriterException e) {
            System.out.println(e.toString());
        }

        findViewById(R.id.qrHome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QRActivity.this, MainActivity.class);
                intent.putExtra("calendar", qrJson);
                intent.putExtra("namedCalendar", namedCalendar);
                startActivity(intent);
            }
        });
    }
}
