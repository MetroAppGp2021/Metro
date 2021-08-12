package com.example.metrocard2020.features;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.metrocard2020.R;
import com.example.metrocard2020.adapter.HistoryRecycleViewAdapter;
import com.example.metrocard2020.adapter.TicketRecycleViewAdapter;
import com.example.metrocard2020.login.ApiConnection;
import com.example.metrocard2020.main.MainActivity;
import com.example.metrocard2020.model.TripModel;
import com.example.metrocard2020.payment.WalletActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryActivity extends AppCompatActivity {
    TextView ViewTripsTextView;
    List<TripModel> trips;
    SharedPreferences preferences;
    String Username, Phone;
    ImageView BackImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        BackImageView = findViewById(R.id.back_image_view);
        ViewTripsTextView = findViewById(R.id.view_trips_text_view);
        preferences = getSharedPreferences("stay_login",MODE_PRIVATE);
        Username = preferences.getString("name","");
        Phone  = preferences.getString("phone", "");

        ViewTripsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trips = new ArrayList<>();
                Call<List<TripModel>> call = ApiConnection.getApiPlaceHolder().getTrips(1L);
                call.enqueue(new Callback<List<TripModel>>() {
                    @Override
                    public void onResponse(Call<List<TripModel>> call, Response<List<TripModel>> response) {
                        if (response.isSuccessful()) {
                            List<TripModel> AllTrips = response.body();
                            if(AllTrips.size() == 0){
                                Toast.makeText(HistoryActivity.this, "There is No tickets revise ticket first", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
                                int year = calendar.get(Calendar.YEAR);
                                int month = calendar.get(Calendar.MONTH) + 1;
                                int day = calendar.get(Calendar.DAY_OF_MONTH);
                                for (TripModel t : AllTrips) {
                                    if(t.getPhone().equals(Phone) && t.getEntry() && t.getExit()){
                                        if(t.getYear() == year && t.getMonth() == month){
                                            trips.add(t);
                                        }
                                    }

                                }
                                initialRecyclerView();
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<List<TripModel>> call, Throwable t) {
                    }
                });
            }
        });
        BackImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HistoryActivity.this, WalletActivity.class));
            }
        });
    }
    public void initialRecyclerView()
    {
        RecyclerView recyclerView = findViewById(R.id.notepad_recycleview);
        HistoryRecycleViewAdapter adapter = new HistoryRecycleViewAdapter(trips,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}