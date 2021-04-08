package com.example.metrocard2020.payment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.metrocard2020.R;
import com.example.metrocard2020.main.MainActivity;
import com.example.metrocard2020.main.NewTraditionalActivity;
import com.example.metrocard2020.main.StationsMapAcivity;

public class TicketReservationActivity extends AppCompatActivity {
    Button PayNowButton;
    ImageView BackImageView;
    AlertDialog dialog;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_reservation);
        PayNowButton = findViewById(R.id.pay_now_button);
        BackImageView = findViewById(R.id.back_image_view);

        builder = new AlertDialog.Builder(TicketReservationActivity.this);
        builder.setTitle("Do you want continue reservation?");

        PayNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(TicketReservationActivity.this, PaymentActivity.class));

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(TicketReservationActivity.this, MainActivity.class));
                    }
                });

                dialog = builder.create();
                dialog.show();
            }
        });


        BackImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TicketReservationActivity.this, StationsMapAcivity.class));
            }
        });
    }
}