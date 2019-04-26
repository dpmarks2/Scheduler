package com.example.scheduler;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class qr_activity extends AppCompatActivity {

    ImageView qrImage;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_activity);

        Intent intent = getIntent();
        String qrJson = intent.getStringExtra("calendar");

        qrImage = (ImageView) findViewById(R.id.qrCode);


        QRGEncoder qrgEncoder = new QRGEncoder(qrJson, null, QRGContents.Type.TEXT, 400);

        try {
            // Getting QR-Code as Bitmap
            bitmap = qrgEncoder.encodeAsBitmap();
            // Setting Bitmap to ImageView
            qrImage.setImageBitmap(bitmap);
        } catch (WriterException e) {
            System.out.println(e.toString());
        }

        findViewById(R.id.qrHome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(qr_activity.this, MainActivity.class);
                intent.putExtra("calendar", qrJson);
                startActivity(intent);
            }
        });
    }
}
