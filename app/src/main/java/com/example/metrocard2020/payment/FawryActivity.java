package com.example.metrocard2020.payment;

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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.metrocard2020.R;
import com.example.metrocard2020.login.ApiConnection;
import com.example.metrocard2020.login.ApiPlaceHolder;
import com.example.metrocard2020.login.RegistrationActivity;
import com.example.metrocard2020.main.MainActivity;
import com.example.metrocard2020.model.UserModel;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FawryActivity extends AppCompatActivity {
    ImageView BackImageView;
    EditText PhoneNumberEditText, ChargeAmountEditText;
    TextView FinishTextView;
    ProgressBar progressBar;
    SharedPreferences preferences;
    boolean  PHONE;
    UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fawry);
        //----------------------------------------------------
        progressBar = (ProgressBar)findViewById(R.id.spin_kit);
        Sprite foldingCube = new Wave();
        progressBar.setIndeterminateDrawable(foldingCube);
        progressBar.setVisibility(View.GONE);
        //----------------------------------
        BackImageView = findViewById(R.id.back_image_view);
        PhoneNumberEditText = findViewById(R.id.phone_number_Edit_Text_fawry);
        ChargeAmountEditText = findViewById(R.id.charge_amount_Edit_text);
        FinishTextView = findViewById(R.id.finish_button);
        preferences = getSharedPreferences("stay_login",MODE_PRIVATE);
        String number  = preferences.getString("phone", "");

        PhoneNumberEditText.setText(number);

        FinishTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ch = ChargeAmountEditText.getText().toString();

                if (TextUtils.isEmpty(ch)) {
                    ChargeAmountEditText.setError("Enter charge value"); }
                else {
                    double charge = Double.parseDouble(ChargeAmountEditText.getText().toString());
                    AlertDialog.Builder alert  = new AlertDialog.Builder(FawryActivity.this);
                    alert.setMessage("Confirming charge process: do you want to add " + charge +" to your account if you agree press confirm")
                            .setCancelable(false)
                            .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    progressBar.setVisibility(View.VISIBLE);
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
                                                    Call<Void> calld = ApiConnection.getApiPlaceHolder().delete(userModel.getId());
                                                    calld.enqueue(new Callback<Void>() {
                                                        @Override
                                                        public void onResponse(Call<Void> call, Response<Void> response) {

                                                        }

                                                        @Override
                                                        public void onFailure(Call<Void> call, Throwable t) {

                                                        }
                                                    });
                                                    UserModel u = new UserModel(userModel.getName(),userModel.getPhone(),userModel.getPassword(),userModel.getNational_id(),userModel.getAccount() + charge,userModel.getFrom_station(), userModel.getTo_station());
                                                    Call<UserModel> calla = ApiConnection.getApiPlaceHolder().addUser(u);
                                                    calla.enqueue(new Callback<UserModel>() {
                                                        @Override
                                                        public void onResponse(Call<UserModel> call, Response<UserModel> response) {

                                                        }

                                                        @Override
                                                        public void onFailure(Call<UserModel> call, Throwable t) {

                                                        }
                                                    });
                                                    ChargeAmountEditText.setText("");
                                                    FinishTextView.setBackgroundResource(R.drawable.green_button);
                                                    FinishTextView.setText("Done");
                                                    progressBar.setVisibility(View.GONE);
                                                }
                                                PHONE = false;
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<List<UserModel>> call, Throwable t) {

                                        }
                                    });

                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    progressBar.setVisibility(View.GONE);
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alertDialog = alert.create();
                    alertDialog.setTitle("Confirmation");
                    alertDialog.show();

                }
            }
        });

        BackImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FawryActivity.this, MainActivity.class));
            }
        });
    }
}