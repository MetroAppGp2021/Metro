package com.example.metrocard2020.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.metrocard2020.R;
import com.example.metrocard2020.login.ApiConnection;
import com.example.metrocard2020.model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewTraditionalActivity extends AppCompatActivity {
    TextView NewTripButton, TraditionalTripButton;
    ImageView BackImageView;
    SharedPreferences preferences;
    boolean  PHONE;
    UserModel userModel;

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
                SharedPreferences preferences1 = getSharedPreferences("station",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences1.edit();
                editor.putString("FromStation","");
                editor.putString("ToStation","");
                editor.apply();
                startActivity(new Intent(NewTraditionalActivity.this,FromToStationsActivity.class));
            }
        });
        TraditionalTripButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences = getSharedPreferences("stay_login",MODE_PRIVATE);
                String number  = preferences.getString("phone", "");
                Call<List<UserModel>> call = ApiConnection.getApiPlaceHolder().userLogin(1L);
                call.enqueue(new Callback<List<UserModel>>() {
                    @Override
                    public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                        if (response.isSuccessful()){
                            List<UserModel> Users = response.body();
                            for (UserModel user: Users){
                                if(user.getPhone().equals(number)){
                                    PHONE = true;
                                    userModel = user;
                                }
                            }
                            if(PHONE) {
                                if(userModel.getFrom_station().equals("") && userModel.getTo_station().equals("")){
                                    Toast.makeText(NewTraditionalActivity.this, "there is No favorite trips. go to new trip first & set as avorite", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    SharedPreferences preferences1 = getSharedPreferences("station",MODE_PRIVATE);
                                    SharedPreferences.Editor editor = preferences1.edit();
                                    editor.putString("FromStation",userModel.getFrom_station());
                                    editor.putString("ToStation",userModel.getTo_station());
                                    editor.apply();
                                    startActivity(new Intent(NewTraditionalActivity.this,FromToStationsActivity.class));
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<UserModel>> call, Throwable t) {

                    }
                });

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