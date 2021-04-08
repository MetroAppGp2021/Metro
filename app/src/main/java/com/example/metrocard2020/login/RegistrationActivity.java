package com.example.metrocard2020.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.metrocard2020.R;
import com.example.metrocard2020.main.MainActivity;

public class RegistrationActivity extends AppCompatActivity {
    EditText UserNameEditText,PhoneNumberEditText,NationalIdEditText,PasswordEditText;
    TextView RegisterButton,LoginTextView;
    ImageView FaceBookLogin,TwitterLogin,GoogleLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        UserNameEditText = findViewById(R.id.user_name_edit_text);
        PhoneNumberEditText = findViewById(R.id.phone_number_edit_text);
        NationalIdEditText = findViewById(R.id.national_id_edit_text);
        PasswordEditText = findViewById(R.id.password_edit_text);
        RegisterButton = findViewById(R.id.register_button);
        LoginTextView = findViewById(R.id.login_text_view);
        FaceBookLogin = findViewById(R.id.facebook_to_login);
        TwitterLogin = findViewById(R.id.twitter_to_login);
        GoogleLogin = findViewById(R.id.google_to_login);

        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = UserNameEditText.getText().toString();
                String phone = PhoneNumberEditText.getText().toString();
                String nationalID = NationalIdEditText.getText().toString();
                String password = PasswordEditText.getText().toString();


                if(TextUtils.isEmpty(user))
                    UserNameEditText.setError("Enter your Name");
                else if(TextUtils.isEmpty(phone))
                    PhoneNumberEditText.setError("Enter Your phone Number");
                else if(TextUtils.isEmpty(nationalID))
                    NationalIdEditText.setError("Enter Your National ID");
                else if(TextUtils.isEmpty(password))
                    PasswordEditText.setError("Enter Your password");
                else {
                    startActivity(new Intent(RegistrationActivity.this, MainActivity.class));

            }
            }
        });
        LoginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });

        FaceBookLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RegistrationActivity.this, "Login with Facebook is done successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
            }
        });
        TwitterLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RegistrationActivity.this, "Login with Twitter is done successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
            }
        });
        TwitterLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RegistrationActivity.this, "Login with Google is done successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
            }
        });
    }
}