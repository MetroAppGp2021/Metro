package com.example.metrocard2020.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.metrocard2020.R;

public class NewTraditionalActivity extends AppCompatActivity {
    TextView NewTripButton, TraditionalTripButton;
    ImageView BackImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_traditional);
        NewTripButton = findViewById(R.id.new_trip_button);
        TraditionalTripButton = findViewById(R.id.traditional_trip_button);
        BackImageView = findViewById(R.id.back_image_view);

        NewTripButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewTraditionalActivity.this,StationsMapAcivity.class));
            }
        });
        TraditionalTripButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // to payment page.
            }
        });
        BackImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewTraditionalActivity.this,MainActivity.class));
            }
        });
    }
}