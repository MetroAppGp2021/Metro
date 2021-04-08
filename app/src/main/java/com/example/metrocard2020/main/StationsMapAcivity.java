package com.example.metrocard2020.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.metrocard2020.R;
import com.example.metrocard2020.payment.TicketReservationActivity;

public class StationsMapAcivity extends AppCompatActivity {
    ImageView BackImageView;
    TextView ConfirmTripTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stations_map_acivity);
        BackImageView = findViewById(R.id.back_image_view);
        ConfirmTripTextView = findViewById(R.id.confirm_trip_text_view);

        BackImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StationsMapAcivity.this,NewTraditionalActivity.class));
            }
        });
        ConfirmTripTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StationsMapAcivity.this, TicketReservationActivity.class));
            }
        });
    }
}