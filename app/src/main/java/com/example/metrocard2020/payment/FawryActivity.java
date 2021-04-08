package com.example.metrocard2020.payment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.metrocard2020.R;
import com.example.metrocard2020.main.MainActivity;

public class FawryActivity extends AppCompatActivity {
    ImageView BackImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fawry);
        BackImageView = findViewById(R.id.back_image_view);
        BackImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FawryActivity.this, MainActivity.class));
            }
        });
    }
}