package com.example.metrocard2020.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.metrocard2020.R;
import com.example.metrocard2020.main.MainActivity;

public class LoginActivity extends AppCompatActivity {
    EditText PhoneNumberEditText,PasswordEditText;
    TextView LoginButton,CreateAccount;
    ImageView FaceBookLogin,TwitterLogin,GoogleLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        PhoneNumberEditText = findViewById(R.id.phone_number_edit_text);
        PasswordEditText = findViewById(R.id.password_edit_text);
        LoginButton = findViewById(R.id.login_button);
        CreateAccount = findViewById(R.id.create_account_text_view);
        FaceBookLogin = findViewById(R.id.facebook_to_login);
        TwitterLogin = findViewById(R.id.twitter_to_login);
        GoogleLogin = findViewById(R.id.google_to_login);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = PhoneNumberEditText.getText().toString();
                String password = PasswordEditText.getText().toString();

                if(TextUtils.isEmpty(phone))
                {
                    PhoneNumberEditText.setError("Enter Number");
                }
                else if(TextUtils.isEmpty(password))
                {
                    PasswordEditText.setError("Enter password");

                }
                else {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
            }
        });

        CreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));

            }
        });
        FaceBookLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Login with Facebook is done successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });
        TwitterLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Login with Twitter is done successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });
        TwitterLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Login with Google is done successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });
    }
}