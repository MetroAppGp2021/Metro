package com.example.metrocard2020.features;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.metrocard2020.R;
import com.example.metrocard2020.login.ApiConnection;
import com.example.metrocard2020.login.LoginActivity;
import com.example.metrocard2020.main.FromToStationsActivity;
import com.example.metrocard2020.main.MainActivity;
import com.example.metrocard2020.model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingActivity extends AppCompatActivity {
    TextView ChangeNameTextView, ChangePasswordTextView,DeleteTextView, FacebookTextView, WhatsTextView, GmailTextView;
    EditText ChangeNameEditText, ChangePasswordEditText;
    SharedPreferences preferences;
    String number;
    Boolean DELETE, PHONE, UPDATE_BY_NAME = false , UPDATE_BY_PASSWORD = false;
    UserModel userModel, userModelToDelete, u, u1;
    String Name,Password;
    ImageView BackImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        BackImageView = findViewById(R.id.back_image_view);
        ChangeNameTextView = findViewById(R.id.change_user_name_text_view);
        ChangePasswordTextView = findViewById(R.id.change_password_text_view);
        DeleteTextView = findViewById(R.id.delete_text_view);
        ChangeNameEditText = findViewById(R.id.user_name_edit_text);
        ChangePasswordEditText = findViewById(R.id.password_edit_text);

        ChangeNameEditText.setVisibility(View.GONE);
        ChangePasswordEditText.setVisibility(View.GONE);

        preferences = getSharedPreferences("stay_login",MODE_PRIVATE);
        number  = preferences.getString("phone", "");

        ChangeNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Name = ChangeNameEditText.getText().toString();
                if(Name.equals("")){
                    ChangeNameEditText.setVisibility(View.VISIBLE);
                    if (TextUtils.isEmpty(Name)) {
                        ChangeNameEditText.setError("Enter User Name"); }
                }
                else{
                    UPDATE_BY_NAME = true;
                    updateUser();
                }
            }
        });

        ChangePasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Password = ChangePasswordEditText.getText().toString();
                /**/

                if(Password.equals("")){
                    ChangePasswordEditText.setVisibility(View.VISIBLE);
                    if (TextUtils.isEmpty(Password)) {
                        ChangePasswordEditText.setError("Enter Password"); }
                    else if (Password.length()< 6){
                        ChangePasswordEditText.setError("Password Should be > 6 digit"); }
                }
                else{
                    UPDATE_BY_PASSWORD = true;
                    updateUser();
                }
            }
        });

        DeleteTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteTextView.setBackgroundResource(R.drawable.red_button);
                AlertDialog.Builder alert = new AlertDialog.Builder(SettingActivity.this);
                alert.setMessage("Deleting process" )
                        .setCancelable(false)
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                SharedPreferences preferences = getSharedPreferences("stay_login",MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("remember","false");
                                editor.putString("name","");
                                editor.putString("phone","");
                                editor.putString("nationalId","");
                                editor.apply();

                                deleteUser();
                                startActivity(new Intent(SettingActivity.this, LoginActivity.class));

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = alert.create();
                alertDialog.setTitle("Confirmation");
                alertDialog.show();
            }
        });
        BackImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, MainActivity.class));
            }
        });
    }

    public void updateUser() {
        Call<List<UserModel>> call = ApiConnection.getApiPlaceHolder().userLogin(1L);
        call.enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                if (response.isSuccessful()) {
                    List<UserModel> Users = response.body();
                    for (UserModel user : Users) {
                        if (user.getPhone().equals(number)) {
                            PHONE = true;
                            userModel = user;
                        }
                    }
                    if (PHONE) {
                        Call<Void> calld = ApiConnection.getApiPlaceHolder().delete(userModel.getId());
                        calld.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                            }
                        });
                        if (UPDATE_BY_NAME) {
                            u = new UserModel(Name, userModel.getPhone(), userModel.getPassword(), userModel.getNational_id(), userModel.getAccount(), userModel.getFrom_station(), userModel.getTo_station());
                            Call<UserModel> calla = ApiConnection.getApiPlaceHolder().addUser(u);
                            calla.enqueue(new Callback<UserModel>() {
                                @Override
                                public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                                }

                                @Override
                                public void onFailure(Call<UserModel> call, Throwable t) {
                                }
                            });
                            ChangeNameEditText.setText("");
                            ChangeNameEditText.setBackgroundResource(R.drawable.green_button);
                        }
                        if (UPDATE_BY_PASSWORD) {
                            u1 = new UserModel(userModel.getName(), userModel.getPhone(),Password, userModel.getNational_id(), userModel.getAccount(), userModel.getFrom_station(), userModel.getTo_station());
                            Call<UserModel> calla = ApiConnection.getApiPlaceHolder().addUser(u1);
                            calla.enqueue(new Callback<UserModel>() {
                                @Override
                                public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                                }

                                @Override
                                public void onFailure(Call<UserModel> call, Throwable t) {
                                }
                            });
                            ChangePasswordEditText.setText("");
                            ChangePasswordEditText.setBackgroundResource(R.drawable.green_button);
                        }

                        UPDATE_BY_NAME = false;
                        UPDATE_BY_PASSWORD = false;

                    }
                }
            }

            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {

            }
        });
    }

    public void deleteUser(){
        Call<List<UserModel>> call = ApiConnection.getApiPlaceHolder().userLogin(1L);
        call.enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                if (response.isSuccessful()){
                    List<UserModel> Users = response.body();
                    for (UserModel user: Users){
                        if(user.getPhone().equals(number)){
                            DELETE = true;
                            userModelToDelete = user;
                        }
                    }
                    if(DELETE) {
                        Call<Void> calld = ApiConnection.getApiPlaceHolder().delete(userModelToDelete.getId());
                        calld.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                            }
                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                            }
                        });
                    }
                }
            }
            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {

            }
        });
    }
}
