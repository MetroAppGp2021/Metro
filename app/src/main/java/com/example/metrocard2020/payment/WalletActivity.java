package com.example.metrocard2020.payment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.metrocard2020.R;
import com.example.metrocard2020.features.HistoryActivity;
import com.example.metrocard2020.features.TicketActivity;
import com.example.metrocard2020.login.ApiConnection;
import com.example.metrocard2020.login.LoginActivity;
import com.example.metrocard2020.main.MainActivity;
import com.example.metrocard2020.model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WalletActivity extends AppCompatActivity {
    TextView AccountTextView, AddMoneyTextView, HistoryTextView;
    SharedPreferences preferences;
    String Username, Phone;
    UserModel user;
    boolean PHONE;
    ImageView BackImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        BackImageView = findViewById(R.id.back_image_view);
        AccountTextView = findViewById(R.id.current_account_text_view);
        AddMoneyTextView = findViewById(R.id.add_money_button);
        HistoryTextView = findViewById(R.id.history_button);
        preferences = getSharedPreferences("stay_login",MODE_PRIVATE);
        Username = preferences.getString("name","");
        Phone  = preferences.getString("phone", "");

        Call<List<UserModel>> call = ApiConnection.getApiPlaceHolder().userLogin(1L);
        call.enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                if (response.isSuccessful()) {
                    List<UserModel> Users = response.body();
                    for (UserModel userModel: Users){
                        if(userModel.getPhone().equals(Phone)){
                            PHONE = true;
                            user = userModel;
                        }
                    }
                    if (PHONE){
                        AccountTextView.setText(String.valueOf(user.getAccount()));
                    }
                }
            }
            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {

            }
        });

        AddMoneyTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WalletActivity.this, FawryActivity.class));
            }
        });
        HistoryTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WalletActivity.this, HistoryActivity.class));
            }
        });
        BackImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WalletActivity.this,MainActivity.class));
            }
        });

    }
}