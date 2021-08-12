package com.example.metrocard2020.login;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.metrocard2020.R;
import com.example.metrocard2020.main.MainActivity;
import com.example.metrocard2020.model.UserModel;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.List;

public class RegistrationActivity extends AppCompatActivity {
    EditText UserNameEditText,PhoneNumberEditText,NationalIdEditText,PasswordEditText;
    TextView RegisterButton,LoginTextView;
    UserModel userModel;
    ProgressBar progressBar;
    boolean  PHONE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        UserNameEditText = findViewById(R.id.user_name_edit_text);PhoneNumberEditText = findViewById(R.id.phone_number_edit_text);NationalIdEditText = findViewById(R.id.national_id_edit_text);
        PasswordEditText = findViewById(R.id.password_edit_text);RegisterButton = findViewById(R.id.register_button);LoginTextView = findViewById(R.id.login_text_view);
        LoginTextView.setVisibility(View.GONE);

        //----------------------------------------------------
        progressBar = (ProgressBar)findViewById(R.id.spin_kit);
        Sprite foldingCube = new Wave();
        progressBar.setIndeterminateDrawable(foldingCube);
        progressBar.setVisibility(View.GONE);
        LoginTextView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        //----------------------------------
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = UserNameEditText.getText().toString();
                String phone = PhoneNumberEditText.getText().toString();
                String nationalID = NationalIdEditText.getText().toString();
                String password = PasswordEditText.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    UserNameEditText.setError("Enter User Name"); }
                else if (TextUtils.isEmpty(phone)){
                    PhoneNumberEditText.setError("Enter Phone Number"); }
                else if (phone.length() != 11){
                    PhoneNumberEditText.setError("Phone Must equal 11 digit starts with 01"); }
                else if (TextUtils.isEmpty(nationalID)){
                    NationalIdEditText.setError("Enter National Id"); }
                else if (nationalID.length() != 14){
                    NationalIdEditText.setError("NationalId Must equal 14 digit"); }
                else if (TextUtils.isEmpty(password)) {
                    PasswordEditText.setError("Enter Password"); }
                else if (password.length()< 6){
                     PasswordEditText.setError("Password Should be > 6 digit"); }
                else {
                    progressBar.setVisibility(View.VISIBLE);
                    userModel = new UserModel(name,phone,password,nationalID,0.0,"","");
                    register(userModel);
                }
            }
        });
        LoginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });

    }
   public void register(UserModel userModel){
       Call<List<UserModel>> call = ApiConnection.getApiPlaceHolder().userLogin(1L);
       call.enqueue(new Callback<List<UserModel>>() {
           @Override
           public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
               if (response.isSuccessful()){
                   List<UserModel> Users = response.body();
                   for (UserModel user: Users){
                       if(user.getPhone().equals(userModel.getPhone())){
                           PHONE = true;}
                   }
                   if(PHONE){
                       progressBar.setVisibility(View.GONE);
                       LoginTextView.setVisibility(View.VISIBLE);
                   }
                   else{
                       Call<UserModel> call2 = ApiConnection.getApiPlaceHolder().singUp(userModel);
                       call2.enqueue(new Callback<UserModel>() {
                           @Override
                           public void onResponse(Call<UserModel> call, Response<UserModel> response) {

                           }

                           @Override
                           public void onFailure(Call<UserModel> call, Throwable t) {

                           }
                       });
                       SharedPreferences preferences = getSharedPreferences("stay_login", MODE_PRIVATE);
                       SharedPreferences.Editor editor = preferences.edit();
                       editor.putString("remember", "true");
                       editor.putString("name", userModel.getName());
                       editor.putString("phone", userModel.getPhone());
                       editor.putString("nationalId",userModel.getNational_id());
                       editor.apply();
                       progressBar.setVisibility(View.GONE);
                       startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                   }
               }
           }
           @Override
           public void onFailure(Call<List<UserModel>> call, Throwable t) {
           }
       });
   }


}