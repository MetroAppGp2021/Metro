package com.example.metrocard2020.features;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.metrocard2020.R;
import com.example.metrocard2020.login.ApiConnection;
import com.example.metrocard2020.login.LoginActivity;
import com.example.metrocard2020.main.MainActivity;
import com.example.metrocard2020.model.TripModel;
import com.example.metrocard2020.model.UserModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserAccountActivity extends AppCompatActivity {
    TextView UserNameTextView, NumberTripsTextView, CostTextView;
    SharedPreferences preferences;
    String Username, Phone;
    double cost;
    ImageView BackImageView;
    List<TripModel> UserTrips;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);
        BackImageView = findViewById(R.id.back_image_view);
        UserNameTextView = findViewById(R.id.user_name_text_view);
        NumberTripsTextView = findViewById(R.id.number_of_trips_text_view);
        CostTextView = findViewById(R.id.cost_text_view);
        UserTrips = new ArrayList<>();

        preferences = getSharedPreferences("stay_login",MODE_PRIVATE);
        Username = preferences.getString("name","");
        Phone  = preferences.getString("phone", "");
        data();
        BackImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserAccountActivity.this,MainActivity.class));
            }
        });
    }

    public void data(){
        //Toast.makeText(this, "" + Phone, Toast.LENGTH_SHORT).show();
        Call<List<TripModel>> call = ApiConnection.getApiPlaceHolder().getTrips(1L);
        call.enqueue(new Callback<List<TripModel>>() {
            @Override
            public void onResponse(Call<List<TripModel>> call, Response<List<TripModel>> response) {
                if(response.isSuccessful()){
                    List<TripModel> Trips = response.body();
                    Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
                    int year = calendar.get(Calendar.YEAR);int month = calendar.get(Calendar.MONTH)+1;
                    for(TripModel tripModel: Trips){
                        if(tripModel.getPhone().equals(Phone)&& tripModel.getEntry() && tripModel.getExit()){
                            if(tripModel.getYear() == year && tripModel.getMonth() == month){
                                UserTrips.add(tripModel);
                                cost += tripModel.getCost();
                            }
                        }
                    }
                    UserNameTextView.setText(Username);
                    NumberTripsTextView.setText(String.valueOf(UserTrips.size()));
                    CostTextView.setText(String.valueOf(cost));
                }
            }

            @Override
            public void onFailure(Call<List<TripModel>> call, Throwable t) {

            }
        });

    }
}