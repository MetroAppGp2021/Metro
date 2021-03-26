package com.example.metrocard2020.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.metrocard2020.R;

public class NewTraditionalActivity extends AppCompatActivity {
    Button NewTripButton, TraditionalTripButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_traditional);
        NewTripButton = findViewById(R.id.new_trip_button);
        TraditionalTripButton = findViewById(R.id.traditional_trip_button);

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
    }
}