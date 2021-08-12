package com.example.metrocard2020.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.metrocard2020.R;
import com.example.metrocard2020.main.MainActivity;
import com.example.metrocard2020.model.UserModel;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity{
    EditText PhoneNumberEditText, PasswordEditText;
    TextView LoginTextView, CreateAccountTextView;
    boolean PHONE, PASSWORD;
    ProgressBar progressBar;
    UserModel user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        PhoneNumberEditText = findViewById(R.id.phone_number_edit_text);
        PasswordEditText = findViewById(R.id.password_edit_text);
        LoginTextView = findViewById(R.id.login_button);
        CreateAccountTextView = findViewById(R.id.create_account_text_view);

        //----------------------------------------------------
        progressBar = (ProgressBar)findViewById(R.id.spin_kit);
        Sprite foldingCube = new Wave();
        progressBar.setIndeterminateDrawable(foldingCube);
        progressBar.setVisibility(View.GONE);
        findViewById(R.id.create_account_text_view).setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        //----------------------------------
        SharedPreferences preferences = getSharedPreferences("stay_login",MODE_PRIVATE);
        String remember = preferences.getString("remember","");
        if(remember.equals("true")){
            startActivity(new Intent(this,MainActivity.class));
        }

        LoginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PHONE = false; PASSWORD = false;
                String phone = PhoneNumberEditText.getText().toString();
                String password = PasswordEditText.getText().toString();

                if (TextUtils.isEmpty(phone)) {
                    PhoneNumberEditText.setError("Enter Number"); }
                else if (phone.length() != 11){
                    PhoneNumberEditText.setError("Phone Must equal 11 digit starts with 01"); }
                else if (TextUtils.isEmpty(password)) {
                    PasswordEditText.setError("Enter password"); }
                else if (password.length()< 6){
                    PasswordEditText.setError("Password Should be > 6 digit"); }
                else {
                    progressBar.setVisibility(View.VISIBLE);
                    login(phone,password);
                }
            }
        });

        CreateAccountTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
            }
        });

    }
   public void login(String phone, String password){
       Call<List<UserModel>> call = ApiConnection.getApiPlaceHolder().userLogin(1L);
       call.enqueue(new Callback<List<UserModel>>() {
           @Override
           public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
               if (response.isSuccessful()) {
                   List<UserModel> Users = response.body();
                   for (UserModel userModel: Users){
                       if(userModel.getPhone().equals(phone)){
                           PHONE = true;}
                       else if(userModel.getPassword().equals(password)){
                           PASSWORD = true;
                       }
                       if (userModel.getPhone().equals(phone) && userModel.getPassword().equals(password)){
                           user = userModel;
                       }

                   }
                   if (PHONE && PASSWORD){
                       progressBar.setVisibility(View.GONE);
                       Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                       intent.putExtra("name",user.getName());
                       startActivity(intent);
                       PasswordEditText.setText("");
                       PhoneNumberEditText.setText("");
                       //-----------------------------
                       SharedPreferences preferences = getSharedPreferences("stay_login",MODE_PRIVATE);
                       SharedPreferences.Editor editor = preferences.edit();
                       editor.putString("remember","true");
                       editor.putString("name",user.getName());
                       editor.putString("phone",user.getPhone());
                       editor.putString("nationalId",user.getNational_id());
                       editor.apply();
                   }
                   else if(PHONE && !PASSWORD){
                       progressBar.setVisibility(View.GONE);
                       PasswordEditText.setText("");
                       Toast.makeText(LoginActivity.this, "INCORRECT password", Toast.LENGTH_SHORT).show();}
                   else if (!PHONE && PASSWORD){
                       progressBar.setVisibility(View.GONE);
                       PhoneNumberEditText.setText("");
                       Toast.makeText(LoginActivity.this, "INCORRECT phone", Toast.LENGTH_SHORT).show();}
                   else{
                       progressBar.setVisibility(View.GONE);
                       PasswordEditText.setText("");
                       PhoneNumberEditText.setText("");
                       Toast.makeText(LoginActivity.this, "Not exist Need TO create account", Toast.LENGTH_SHORT).show();
                       findViewById(R.id.create_account_text_view).setVisibility(View.VISIBLE);}
               }
           }
           @Override
           public void onFailure(Call<List<UserModel>> call, Throwable t) {

           }
       });
   }
}
