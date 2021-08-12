package com.example.metrocard2020.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.metrocard2020.R;

public class Line3 extends AppCompatActivity {
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private TextView ConfirmLine3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line3);
        radioGroup = (RadioGroup) findViewById(R.id.radio_groupLine3);
        ConfirmLine3 = (TextView) findViewById(R.id.confirm_line3_text_view);
        SharedPreferences preferences = getSharedPreferences("station",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        ConfirmLine3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                if (selectedId < 0){
                    Toast.makeText(Line3.this, "select station" , Toast.LENGTH_SHORT).show();
                }
                else{
                    String disF = getIntent().getStringExtra("From");
                    String disT = getIntent().getStringExtra("To");
                    if(disF != null){
                        if(disF.equals("from")){
                            //-----------------------------
                            editor.putString("base","");
                            editor.putString("FromStation",radioButton.getText().toString());
                            editor.apply();
                            Intent intent = new Intent(Line3.this , FromToStationsActivity.class);
                            startActivity(intent);}
                    }
                    else if(disT != null){
                        if(disT.equals("to")){
                            //-----------------------------
                            editor.putString("ToStation",radioButton.getText().toString());
                            editor.apply();

                            Intent intent = new Intent(Line3.this , FromToStationsActivity.class);
                            startActivity(intent);}
                    }
                }

            }
        });
    }
}