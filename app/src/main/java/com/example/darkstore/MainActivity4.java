package com.example.darkstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity4 extends AppCompatActivity {

    ImageView imgpeak;
    ImageView imgsort;
    ImageView imgleanprep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        imgpeak = (ImageView) findViewById(R.id.img_peak);
        imgsort = (ImageView) findViewById(R.id.img_sorting);
        imgleanprep = (ImageView) findViewById(R.id.img_preparation);

        imgpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(MainActivity4.this, MainActivity5.class);
                startActivity(registerIntent);
                finish();
            }
        });
        imgsort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(MainActivity4.this, MainActivity3.class);
                startActivity(registerIntent);
                finish();
            }
        });

        imgleanprep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(MainActivity4.this, MainActivity6.class);
                startActivity(registerIntent);
                finish();
            }
        });
    }
}