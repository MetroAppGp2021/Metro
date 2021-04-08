package com.example.metrocard2020.payment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.metrocard2020.R;
import com.example.metrocard2020.main.StationsMapAcivity;

public class PaymentActivity extends AppCompatActivity {
    ImageView BackImageView;
    TextView GetQRCodeTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        BackImageView = findViewById(R.id.back_image_view);
        GetQRCodeTextView = findViewById(R.id.get_qr_code_text_view);

        GetQRCodeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PaymentActivity.this, QRActivity.class));
            }
        });
        BackImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PaymentActivity.this, TicketReservationActivity.class));
            }
        });
    }
}