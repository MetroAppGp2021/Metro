package com.example.metrocard2020.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.metrocard2020.R;

public class LoginAndRegistrationActivity extends AppCompatActivity {
    TextView LoginButton,RegistrationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_and_registration);
        LoginButton = findViewById(R.id.login_button);
        RegistrationButton = findViewById(R.id.register_button);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginAndRegistrationActivity.this,LoginActivity.class));
            }
        });

        RegistrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginAndRegistrationActivity.this,RegistrationActivity.class));
            }
        });
    }
}