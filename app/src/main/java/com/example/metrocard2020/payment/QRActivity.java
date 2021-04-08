package com.example.metrocard2020.payment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.metrocard2020.R;

public class QRActivity extends AppCompatActivity {
    ImageView BackImageView;
    ImageView QRImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_r);
        BackImageView = findViewById(R.id.back_image_view);
        QRImageView = findViewById(R.id.qr_image_view);

        BackImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QRActivity.this, PaymentActivity.class));
            }
        });
        
        QRImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QRImageView.setBackgroundResource(R.drawable.round_button);
            }
        });

    }
}