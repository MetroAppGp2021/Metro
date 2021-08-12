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
import com.example.metrocard2020.adapter.TicketRecycleViewAdapter;
import com.example.metrocard2020.login.ApiConnection;
import com.example.metrocard2020.main.MainActivity;
import com.example.metrocard2020.main.NewTraditionalActivity;
import com.example.metrocard2020.model.TripModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TicketActivity extends AppCompatActivity {
    SharedPreferences preferences;
    List<TripModel> trips;
    String phone;
    ImageView BackImageView;
    TextView ViewTicketsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        BackImageView = findViewById(R.id.back_image_view);
        ViewTicketsTextView = findViewById(R.id.view_tickets_text_view);
        preferences = getSharedPreferences("stay_login",MODE_PRIVATE);
        phone  = preferences.getString("phone", "");

        ViewTicketsTextView.setOnClickListener(new View.OnClickListener() {
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
                                Toast.makeText(TicketActivity.this, "There is No tickets revise ticket first", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
                                int year = calendar.get(Calendar.YEAR);
                                int month = calendar.get(Calendar.MONTH) + 1;
                                int day = calendar.get(Calendar.DAY_OF_MONTH);
                                for (TripModel t : AllTrips) {
                                    if (t.getPhone().equals(phone) && t.getYear() == year && t.getMonth() == month && (t.getDay() == day || t.getDay() == day - 1)) {
                                        if (!t.getEntry() || !t.getExit()) {
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
                startActivity(new Intent(TicketActivity.this,MainActivity.class));
            }
        });
    }

    public void initialRecyclerView()
    {
        RecyclerView recyclerView = findViewById(R.id.notepad_recycleview);
        TicketRecycleViewAdapter adapter = new TicketRecycleViewAdapter(trips,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


}