package com.example.metrocard2020.main;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.metrocard2020.R;
import com.example.metrocard2020.features.TicketActivity;
import com.example.metrocard2020.login.ApiConnection;
import com.example.metrocard2020.model.TripModel;
import com.example.metrocard2020.model.UserModel;
import com.example.metrocard2020.payment.FawryActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FromToStationsActivity extends AppCompatActivity {
    Spinner spin1, spin2;
    TextView FromTextView, ToTextView, ConfirmTripTextView,ViewPriceTextView, NumberStationTextView, TicketPriceTextView, TextView1 ,TextView2;
    SharedPreferences preferences, preferences1, preferences3;
    ImageView BackImageView;
    boolean  ACCOUNT, UPDATEACC;
    UserModel userAcc, userUpdateAcc;
    CheckBox FavTrip;
    String number;
    int numberOfStation = 0;
    double ticketPrice = 0.0, age = 0.0, acc = 0.0;


    ArrayList<String> ArrayHelwanMarg = new ArrayList<>();ArrayList<String> ArrayMounibShobra = new ArrayList<>();ArrayList<String> ArrayAttabaAirport = new ArrayList<>();ArrayList<String> Arrayelmonibhelwan = new ArrayList<>();ArrayList<String> Arrayeshobracairoair = new ArrayList<>();
    ArrayList<String> Arrayeshobrahelwan = new ArrayList<>();ArrayList<String> Arrayeshobramarg = new ArrayList<>();ArrayList<String> Arrayemargcairoair = new ArrayList<>();
    ArrayList<String> Arrayecairoairhelwan = new ArrayList<>();ArrayList<String> ArrayelmonibMarg = new ArrayList<>();ArrayList<String> Arrayelmonibcairoair = new ArrayList<>();
    ArrayList<String> test = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from_to_stations);
        //_____________________________________________________
        ArrayHelwanMarg.add("New EL-Marg");
        ArrayHelwanMarg.add("El-Marg");
        ArrayHelwanMarg.add("Ezbt El_Nakhl");
        ArrayHelwanMarg.add("Ain-Shams");
        ArrayHelwanMarg.add("EL_Matrya");
        ArrayHelwanMarg.add("Helmyet EL-Zaiton");
        ArrayHelwanMarg.add("Hadayeq EL-Zaiton");
        ArrayHelwanMarg.add("Saray EL_Qobba");
        ArrayHelwanMarg.add("Hammat EL-Qobba");
        ArrayHelwanMarg.add("kobri EL-Qobba");
        ArrayHelwanMarg.add("Mansheit EL-Sadr");
        ArrayHelwanMarg.add("EL_Demerdash");
        ArrayHelwanMarg.add("Ghammra");
        ArrayHelwanMarg.add("AL-Shohadaa");
        ArrayHelwanMarg.add("Orabi");
        ArrayHelwanMarg.add("Nasser");
        ArrayHelwanMarg.add("Sadat");
        ArrayHelwanMarg.add("Saad zaghloul");
        ArrayHelwanMarg.add("Sayyeda-Zienb");
        ArrayHelwanMarg.add("EL-Malk EL-Saleh");
        ArrayHelwanMarg.add("Mar-Giris");
        ArrayHelwanMarg.add("EL-Zaharaa");
        ArrayHelwanMarg.add("Dar EL-Salam");
        ArrayHelwanMarg.add("Hadayeq EL-Maadi");
        ArrayHelwanMarg.add("Maadi");
        ArrayHelwanMarg.add("Sakanat EL-Maadi");
        ArrayHelwanMarg.add("Tora EL-Balad");
        ArrayHelwanMarg.add("Kozzika");
        ArrayHelwanMarg.add("Tora EL-Asmant");
        ArrayHelwanMarg.add("EL_Massara");
        ArrayHelwanMarg.add("Hadayeq-Helwan");
        ArrayHelwanMarg.add("Wadii-Hof");
        ArrayHelwanMarg.add("Helwan-University");
        ArrayHelwanMarg.add("Ain-Helwan");
        ArrayHelwanMarg.add("Helwan");
        //line2
        ArrayMounibShobra.add("Shobra");
        ArrayMounibShobra.add("Koleyet el-Zeraa");
        ArrayMounibShobra.add("Mezallat");
        ArrayMounibShobra.add("Khalafawy");
        ArrayMounibShobra.add("Sainte Teresa");
        ArrayMounibShobra.add("Road el-farag");
        ArrayMounibShobra.add("Massara");
        ArrayMounibShobra.add("AL-Shohadaa");
        ArrayMounibShobra.add("Attaba");
        ArrayMounibShobra.add("Mohamed Naguib");
        ArrayMounibShobra.add("Sadat");
        ArrayMounibShobra.add("Opera");
        ArrayMounibShobra.add("Dokki");
        ArrayMounibShobra.add("Bohooth");
        ArrayMounibShobra.add("Cairo University");
        ArrayMounibShobra.add("Faisal");
        ArrayMounibShobra.add("Giza");
        ArrayMounibShobra.add("Giza Suburbs");
        ArrayMounibShobra.add("Sakiat Mekki");
        ArrayMounibShobra.add("El Mounib");
        //line3
        ArrayAttabaAirport.add("Cairo-Airport");
        ArrayAttabaAirport.add("Adly-Mansour");
        ArrayAttabaAirport.add("Hiksatab");
        ArrayAttabaAirport.add("Omar ebn-EL-Khatab");
        ArrayAttabaAirport.add("Qebaa");
        ArrayAttabaAirport.add("Hisham-Barakat");
        ArrayAttabaAirport.add("EL-Nozha");
        ArrayAttabaAirport.add("Nadi EL-Shams");
        ArrayAttabaAirport.add("Alf-Maskan");
        ArrayAttabaAirport.add("Heleioblies");
        ArrayAttabaAirport.add("Haraoun");
        ArrayAttabaAirport.add("EL-Aharam");
        ArrayAttabaAirport.add("Kolit EL-Banat");
        ArrayAttabaAirport.add("Cairo-Stadieum");
        ArrayAttabaAirport.add("EL-Maaraad");
        ArrayAttabaAirport.add("Abbasia");
        ArrayAttabaAirport.add("Abdo-Basha");
        ArrayAttabaAirport.add("EL-Geish");
        ArrayAttabaAirport.add("Bab EL-Shaaria");
        ArrayAttabaAirport.add("Attaba");
        //Line 1 && Line 2
        ArrayelmonibMarg.add("El Mounib");
        ArrayelmonibMarg.add("Sakiat Mekki");
        ArrayelmonibMarg.add("Giza Surburbus");
        ArrayelmonibMarg.add("Giza");
        ArrayelmonibMarg.add("Faisal");
        ArrayelmonibMarg.add("Cairo University");
        ArrayelmonibMarg.add("Bohooth");
        ArrayelmonibMarg.add("Dokki");
        ArrayelmonibMarg.add("Opera");
        ArrayelmonibMarg.add("Sadat");
        ArrayelmonibMarg.add("Nasser");
        ArrayelmonibMarg.add("Orabi");
        ArrayelmonibMarg.add("AL-Shohadaa");
        ArrayelmonibMarg.add("Ghammra");
        ArrayelmonibMarg.add("EL-Demerdash");
        ArrayelmonibMarg.add("Mansheit EL-Sadr");
        ArrayelmonibMarg.add("kobri EL-Qobba");
        ArrayelmonibMarg.add("Hammat EL-Qobba");
        ArrayelmonibMarg.add("Saray EL-Qobba");
        ArrayelmonibMarg.add("Hadayeq EL-Zaiton");
        ArrayelmonibMarg.add("Helmyet EL-zaiton");
        ArrayelmonibMarg.add("EL-Matrya");
        ArrayelmonibMarg.add("Ain-Shams");
        ArrayelmonibMarg.add("Ezbet EL-Nakhl");
        ArrayelmonibMarg.add("EL-Marag");
        ArrayelmonibMarg.add("New EL-Marag");
        //Line 2 && Line 3
        Arrayelmonibcairoair.add("El Mounib");
        Arrayelmonibcairoair.add("Sakiat Mekki");
        Arrayelmonibcairoair.add("Giza Surburbus");
        Arrayelmonibcairoair.add("Giza");
        Arrayelmonibcairoair.add("Faisal");
        Arrayelmonibcairoair.add("Cairo University");
        Arrayelmonibcairoair.add("Bohooth");
        Arrayelmonibcairoair.add("Dokki");
        Arrayelmonibcairoair.add("Opera");
        Arrayelmonibcairoair.add("Sadat");
        Arrayelmonibcairoair.add("Mohamed Naguib");
        Arrayelmonibcairoair.add("Attaba");
        Arrayelmonibcairoair.add("Bab EL-Shaaria");
        Arrayelmonibcairoair.add("EL-Geish");
        Arrayelmonibcairoair.add("Abdo-Basha");
        Arrayelmonibcairoair.add("Cairo-Stadieum");
        Arrayelmonibcairoair.add("Kolit EL-Banat");
        Arrayelmonibcairoair.add("EL-Aharam");
        Arrayelmonibcairoair.add("Haraoun");
        Arrayelmonibcairoair.add("Heleioblies");
        Arrayelmonibcairoair.add("Alf-Maskan");
        Arrayelmonibcairoair.add("Nadi EL-Shams");
        Arrayelmonibcairoair.add("EL-Nozha");
        Arrayelmonibcairoair.add("Hisham-Barakat");
        Arrayelmonibcairoair.add("Qebaa");
        Arrayelmonibcairoair.add("Omar ebn-EL-Khatab");
        Arrayelmonibcairoair.add("Hiksatab");
        Arrayelmonibcairoair.add("Adly-Mansour");
        Arrayelmonibcairoair.add("Cairo-Airport");
        // Line 1 && Line 2
        Arrayelmonibhelwan.add("El Mounib");
        Arrayelmonibhelwan.add("Sakiat Mekki");
        Arrayelmonibhelwan.add("Giza Surburbus");
        Arrayelmonibhelwan.add("Giza");
        Arrayelmonibhelwan.add("Faisal");
        Arrayelmonibhelwan.add("Cairo University");
        Arrayelmonibhelwan.add("Bohooth");
        Arrayelmonibhelwan.add("Dokki");
        Arrayelmonibhelwan.add("Opera");
        Arrayelmonibhelwan.add("Sadat");
        Arrayelmonibhelwan.add("Saad zaghloul");
        Arrayelmonibhelwan.add("Sayyeda-Zienb");
        Arrayelmonibhelwan.add("EL-Malk EL-Saleh");
        Arrayelmonibhelwan.add("Mar-Giris");
        Arrayelmonibhelwan.add("EL-Zaharaa");
        Arrayelmonibhelwan.add("Dar EL-Salam");
        Arrayelmonibhelwan.add("Hadayeq EL-Maadi");
        Arrayelmonibhelwan.add("Maadi");
        Arrayelmonibhelwan.add("Sakanat EL-Maadi");
        Arrayelmonibhelwan.add("Tora EL-Balad");
        Arrayelmonibhelwan.add("Kozzika");
        Arrayelmonibhelwan.add("Tora EL-Asmant");
        Arrayelmonibhelwan.add("EL_Massara");
        Arrayelmonibhelwan.add("Hadayeq-Helwan");
        Arrayelmonibhelwan.add("Wadii-Hof");
        Arrayelmonibhelwan.add("Helwan-University");
        Arrayelmonibhelwan.add("Ain-Helwan");
        Arrayelmonibhelwan.add("Helwan");
        // Line 2 && Line 3
        Arrayeshobracairoair.add("Shobra");
        Arrayeshobracairoair.add("Koleyet el-Zeraa");
        Arrayeshobracairoair.add("Mezallat");
        Arrayeshobracairoair.add("Khalafawy");
        Arrayeshobracairoair.add("Sainte Teresa");
        Arrayeshobracairoair.add("Road el-farag");
        Arrayeshobracairoair.add("Massara");
        Arrayeshobracairoair.add("Al-Shohadaa");
        Arrayeshobracairoair.add("Attaba");
        Arrayeshobracairoair.add("Bab EL-Shaaria");
        Arrayeshobracairoair.add("EL-Geish");
        Arrayeshobracairoair.add("Abdo-Basha");
        Arrayeshobracairoair.add("Abbasia");
        Arrayeshobracairoair.add("EL-Maaraad");
        Arrayeshobracairoair.add("Cairo-Stadieum");
        Arrayeshobracairoair.add("Kolit EL-Banat");
        Arrayeshobracairoair.add("EL-Aharam");
        Arrayeshobracairoair.add("Haraoun");
        Arrayeshobracairoair.add("Heleioblies");
        Arrayeshobracairoair.add("Alf-Maskan");
        Arrayeshobracairoair.add("Nadi EL-Shams");
        Arrayeshobracairoair.add("EL-Nozha");
        Arrayeshobracairoair.add("Hisham-Barakat");
        Arrayeshobracairoair.add("Qebaa");
        Arrayeshobracairoair.add("Omar ebn-EL-Khatab");
        Arrayeshobracairoair.add("Hiksatab");
        Arrayeshobracairoair.add("Adly-Mansour");
        Arrayeshobracairoair.add("Cairo-Airport");
        // Line 2 && Line 1
        //ArrayelmonibMarg.add("El Mounib");ArrayelmonibMarg.add("Sakiat Mekki");ArrayelmonibMarg.add("Giza Surburbus");ArrayelmonibMarg.add("Giza");ArrayelmonibMarg.add("Faisal");ArrayelmonibMarg.add("Cairo University");ArrayelmonibMarg.add("Sadat");ArrayelmonibMarg.add("Nasser");ArrayelmonibMarg.add("orabi");ArrayelmonibMarg.add("elshohadaa");ArrayelmonibMarg.add("Ghamra");ArrayelmonibMarg.add("EL-Demerdash");ArrayelmonibMarg.add("Mansheit EL-Sadr");ArrayelmonibMarg.add("kobri EL-Qobba");ArrayelmonibMarg.add("Hammat EL-Qobba");ArrayelmonibMarg.add("Saraaay EL-Qobba");ArrayelmonibMarg.add("Hadayeq EL-Zaiton");ArrayelmonibMarg.add("Helmyet EL-zaiton");ArrayelmonibMarg.add("EL-Matrya");ArrayelmonibMarg.add("Ain-Shams");ArrayelmonibMarg.add("Ezbet EL-Nakhl");ArrayelmonibMarg.add("EL-Marag");ArrayelmonibMarg.add("New EL-Marag");
        // Line 2 && Line 1
        Arrayeshobrahelwan.add("Shobra");
        Arrayeshobrahelwan.add("Koleyet el-Zeraa");
        Arrayeshobrahelwan.add("Mezallat");
        Arrayeshobrahelwan.add("Khalafawy");
        Arrayeshobrahelwan.add("Sainte Teresa");
        Arrayeshobrahelwan.add("Road el-farag");
        Arrayeshobrahelwan.add("Massara");
        Arrayeshobrahelwan.add("AL-Shohadaa");
        Arrayeshobrahelwan.add("Attaba");
        Arrayeshobrahelwan.add("Mohamed Naguib");
        Arrayeshobrahelwan.add("Sadat");
        Arrayeshobrahelwan.add("Saad zaghloul");
        Arrayeshobrahelwan.add("Sayyeda-Zienb");
        Arrayeshobrahelwan.add("EL-Malk EL-Saleh");
        Arrayeshobrahelwan.add("Mar-Giris");
        Arrayeshobrahelwan.add("EL-Zaharaa");
        Arrayeshobrahelwan.add("Dar EL-Salam");
        Arrayeshobrahelwan.add("Hadayeq EL-Maadi");
        Arrayeshobrahelwan.add("Maadi");
        Arrayeshobrahelwan.add("Sakanat EL-Maadi");
        Arrayeshobrahelwan.add("Tora EL-Balad");
        Arrayeshobrahelwan.add("Kozzika");
        Arrayeshobrahelwan.add("Tora EL-Asmant");
        Arrayeshobrahelwan.add("EL_Massara");
        Arrayeshobrahelwan.add("Hadayeq-Helwan");
        Arrayeshobrahelwan.add("Wadii-Hof");
        Arrayeshobrahelwan.add("Helwan-University");
        Arrayeshobrahelwan.add("Ain-Helwan");
        Arrayeshobrahelwan.add("Helwan");
        //Line 2 && Line 1
        Arrayeshobramarg.add("Shobra");
        Arrayeshobramarg.add("Koleyet el-Zeraa");
        Arrayeshobramarg.add("Mezallat");
        Arrayeshobramarg.add("Khalafawy");
        Arrayeshobramarg.add("Sainte Teresa");
        Arrayeshobramarg.add("Road el-farag");
        Arrayeshobramarg.add("Massara");
        Arrayeshobramarg.add("AL-Shohadaa");
        Arrayeshobramarg.add("Ghammra");
        Arrayeshobramarg.add("EL-Demerdash");
        Arrayeshobramarg.add("Mansheit EL-Sadr");
        Arrayeshobramarg.add("kobri EL-Qobba");
        Arrayeshobramarg.add("Hammat EL-Qobba");
        Arrayeshobramarg.add("Saray EL-Qobba");
        Arrayeshobramarg.add("Hadayeq EL-Zaiton");
        Arrayeshobramarg.add("Helmyet EL-zaiton");
        Arrayeshobramarg.add("EL-Matrya");
        Arrayeshobramarg.add("Ain-Shams");
        Arrayeshobramarg.add("Ezbet EL-Nakhl");
        Arrayeshobramarg.add("EL-Marag");
        Arrayeshobramarg.add("New EL-Marag");
        // Line 1 && Line 3
        Arrayemargcairoair.add("New EL-Marag");
        Arrayemargcairoair.add("EL-Marag");
        Arrayemargcairoair.add("Ezbet EL-Nakhl");
        Arrayemargcairoair.add("Ain-Shams");
        Arrayemargcairoair.add("EL-Matrya");
        Arrayemargcairoair.add("Helmyet EL-zaiton");
        Arrayemargcairoair.add("Hadayeq EL-Zaiton");
        Arrayemargcairoair.add("Saray EL-Qobba");
        Arrayemargcairoair.add("Hammat EL-Qobba");
        Arrayemargcairoair.add("kobri EL-Qobba");
        Arrayemargcairoair.add("Mansheit EL-Sadr");
        Arrayemargcairoair.add("EL-Demerdash");
        Arrayemargcairoair.add("Ghammra");
        Arrayemargcairoair.add("AL-Shohadaa");
        Arrayemargcairoair.add("Attaba");
        Arrayemargcairoair.add("Bab EL-Shaaria");
        Arrayemargcairoair.add("EL-Geish");
        Arrayemargcairoair.add("Abdo-Basha");
        Arrayemargcairoair.add("Abbasia");
        Arrayemargcairoair.add("EL-Maaraad");
        Arrayemargcairoair.add("Cairo-Stadieum");
        Arrayemargcairoair.add("Kolit EL-Banat");
        Arrayemargcairoair.add("EL-Aharam");
        Arrayemargcairoair.add("Haraoun");
        Arrayemargcairoair.add("Heleioblies");
        Arrayemargcairoair.add("Alf-Maskan");
        Arrayemargcairoair.add("Nadi EL-Shams");
        Arrayemargcairoair.add("EL-Nozha");
        Arrayemargcairoair.add("Hisham-Barakat");
        Arrayemargcairoair.add("Qebaa");
        Arrayemargcairoair.add("Omar ebn-EL-Khatab");
        Arrayemargcairoair.add("Hiksatab");
        Arrayemargcairoair.add("Adly-Mansour");
        Arrayemargcairoair.add("Cairo-Airport");
        // Line 1 && Line3
        Arrayecairoairhelwan.add("Cairo-Airport");
        Arrayecairoairhelwan.add("Adly-Mansour");
        Arrayecairoairhelwan.add("EL-Nozha");
        Arrayecairoairhelwan.add("Nadi EL-Shams");
        Arrayecairoairhelwan.add("Alf-Maskan");
        Arrayecairoairhelwan.add("Heleioblies");
        Arrayecairoairhelwan.add("Haraoun");
        Arrayecairoairhelwan.add("EL-Aharam");
        Arrayecairoairhelwan.add("Kolit EL-Banat");
        Arrayecairoairhelwan.add("Cairo-Stadieum");
        Arrayecairoairhelwan.add("EL-Maaraad");
        Arrayecairoairhelwan.add("Abbasia");
        Arrayecairoairhelwan.add("Abdo-Basha");
        Arrayecairoairhelwan.add("EL-Geish");
        Arrayecairoairhelwan.add("Bab EL-Shaaria");
        Arrayecairoairhelwan.add("Attaba");
        Arrayecairoairhelwan.add("Mohamed Naguib");
        Arrayecairoairhelwan.add("Sadat");
        Arrayecairoairhelwan.add("Saad zaghloul");
        Arrayecairoairhelwan.add("Sayyeda-Zienb");
        Arrayecairoairhelwan.add("EL-Malk EL-Saleh");
        Arrayecairoairhelwan.add("Mar-Giris");
        Arrayecairoairhelwan.add("EL-Zaharaa");
        Arrayecairoairhelwan.add("Dar EL-Salam");
        Arrayecairoairhelwan.add("Hadayeq EL-Maadi");
        Arrayecairoairhelwan.add("Maadi");
        Arrayecairoairhelwan.add("Sakanat EL-Maadi");
        Arrayecairoairhelwan.add("Tora EL-Balad");
        Arrayecairoairhelwan.add("Kozzika");
        Arrayecairoairhelwan.add("Tora EL-Asmant");
        Arrayecairoairhelwan.add("EL_Massara");
        Arrayecairoairhelwan.add("Hadayeq-Helwan");
        Arrayecairoairhelwan.add("Wadii-Hof");
        Arrayecairoairhelwan.add("Helwan-University");
        Arrayecairoairhelwan.add("Ain-Helwan");
        Arrayecairoairhelwan.add("Helwan");
        //_____________________________________________________

        String[] users = {"Select Line", "Line 1", "Line 2", "Line 3"};

        spin1 = (Spinner) findViewById(R.id.spinner1);
        spin2 = (Spinner) findViewById(R.id.spinner2);
        FromTextView = findViewById(R.id.from_text_view);
        ViewPriceTextView = findViewById(R.id.view_price_text_view);
        ToTextView = findViewById(R.id.to_text_view);
        BackImageView = findViewById(R.id.back_image_view);
        ConfirmTripTextView = findViewById(R.id.confirm_trip_text_view);
        NumberStationTextView = findViewById(R.id.number_of_stations_text_view);
        TicketPriceTextView = findViewById(R.id.ticket_price_text_view);
        TextView1 = findViewById(R.id.textView1); TextView2 = findViewById(R.id.textView2);

        FavTrip = findViewById(R.id.checkbox_favorite_trip);
        NumberStationTextView.setVisibility(View.GONE);
        TicketPriceTextView.setVisibility(View.GONE);

        TextView1.setVisibility(View.GONE); TextView2.setVisibility(View.GONE);

        preferences = getSharedPreferences("station",MODE_PRIVATE);
        String FromStation = preferences.getString("FromStation","");
        String ToStation = preferences.getString("ToStation","");
        String base = preferences.getString("base","");
        preferences1 = getSharedPreferences("stay_login",MODE_PRIVATE);
        String national = preferences1.getString("nationalId","");
        number  = preferences1.getString("phone", "");


        preferences3 = getSharedPreferences("ticket",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences3.edit();

        if(!base.equals("")){
            Toast.makeText(this, "Nearest station: " + base, Toast.LENGTH_SHORT).show();
            FromTextView.setText(base);
        }
        if (FromStation != null){
            FromTextView.setText(FromStation);
        }
        if(ToStation != null){
            ToTextView.setText(ToStation);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(adapter);
        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
                ((TextView) parent.getChildAt(0)).setTextSize(12);
                switch (position){
                    case 1:
                        Intent intent = new Intent(new Intent(FromToStationsActivity.this, Line1.class));
                        intent.putExtra("From","from");
                        startActivity(intent);
                        break;
                    case 2:
                        Intent intent1 = new Intent(new Intent(FromToStationsActivity.this, Line2.class));
                        intent1.putExtra("From","from");
                        startActivity(intent1);
                        break;
                    case 3:
                        Intent intent2 = new Intent(new Intent(FromToStationsActivity.this, Line3.class));
                        intent2.putExtra("From","from");
                        startActivity(intent2);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spin2.setAdapter(adapter);
        spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
                ((TextView) parent.getChildAt(0)).setTextSize(12);
                switch (position){
                    case 1:
                        Intent intent = new Intent(new Intent(FromToStationsActivity.this, Line1.class));
                        intent.putExtra("To","to");
                        startActivity(intent);
                        break;
                    case 2:
                        Intent intent1 = new Intent(new Intent(FromToStationsActivity.this, Line2.class));
                        intent1.putExtra("To","to");
                        startActivity(intent1);
                        break;
                    case 3:
                        Intent intent2 = new Intent(new Intent(FromToStationsActivity.this, Line3.class));
                        intent2.putExtra("To","to");
                        startActivity(intent2);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ViewPriceTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String formtext = FromTextView.getText().toString();
                String totext = ToTextView.getText().toString();
                if (TextUtils.isEmpty(formtext)) {
                    FromTextView.setError("Please Select Your From station"); }
                else if (TextUtils.isEmpty(totext)) {
                    ToTextView.setError("Please Select Your To station"); }
                else{
                    NumberStationTextView.setVisibility(View.VISIBLE);
                    TicketPriceTextView.setVisibility(View.VISIBLE);
                    NumberStationTextView.setVisibility(View.VISIBLE);
                    TextView1.setVisibility(View.VISIBLE); TextView2.setVisibility(View.VISIBLE);

                    if (ArrayHelwanMarg.contains(formtext) && ArrayHelwanMarg.contains(totext)){
                        numberOfStation = ArrayHelwanMarg.indexOf(totext) - ArrayHelwanMarg.indexOf(formtext);
                    }
                    else if(ArrayMounibShobra.contains(formtext) && ArrayMounibShobra.contains(totext)) {
                        numberOfStation = ArrayMounibShobra.indexOf(totext) - ArrayMounibShobra.indexOf(formtext);
                    }
                    else if(ArrayAttabaAirport.contains(formtext) && ArrayAttabaAirport.contains(totext)) {
                        numberOfStation = ArrayAttabaAirport.indexOf(totext) - ArrayAttabaAirport.indexOf(formtext);
                    }
                    else if(ArrayelmonibMarg.contains(formtext) && ArrayelmonibMarg.contains(totext)) {
                        numberOfStation = ArrayelmonibMarg.indexOf(totext) - ArrayelmonibMarg.indexOf(formtext);
                    }
                    else if(Arrayelmonibcairoair.contains(formtext) && Arrayelmonibcairoair.contains(totext)) {
                        numberOfStation = Arrayelmonibcairoair.indexOf(totext) - Arrayelmonibcairoair.indexOf(formtext);
                    }
                    else if(Arrayelmonibhelwan.contains(formtext) && Arrayelmonibhelwan.contains(totext)) {
                        numberOfStation = Arrayelmonibhelwan.indexOf(totext) - Arrayelmonibhelwan.indexOf(formtext);
                    }
                    else if(Arrayeshobracairoair.contains(formtext) && Arrayeshobracairoair.contains(totext)) {
                        numberOfStation = Arrayeshobracairoair.indexOf(totext) - Arrayeshobracairoair.indexOf(formtext);
                    }
                    else if(Arrayeshobrahelwan.contains(formtext) && Arrayeshobrahelwan.contains(totext)) {
                        numberOfStation = Arrayeshobrahelwan.indexOf(totext) - Arrayeshobrahelwan.indexOf(formtext);
                    }
                    else if(Arrayeshobramarg.contains(formtext) && Arrayeshobramarg.contains(totext)) {
                        numberOfStation = Arrayeshobramarg.indexOf(totext) - Arrayeshobramarg.indexOf(formtext);
                    }
                    else if(Arrayemargcairoair.contains(formtext) && Arrayemargcairoair.contains(totext)) {
                        numberOfStation = Arrayemargcairoair.indexOf(totext) - Arrayemargcairoair.indexOf(formtext);
                    }
                    else if(Arrayecairoairhelwan.contains(formtext) && Arrayecairoairhelwan.contains(totext)) {
                        numberOfStation = Arrayecairoairhelwan.indexOf(totext) - Arrayecairoairhelwan.indexOf(formtext);
                    }
                    else if(test.contains(formtext) && test.contains(totext)) {
                        numberOfStation = test.indexOf(totext) - test.indexOf(formtext);
                    }

                    NumberStationTextView.setText(String.valueOf(Math.abs(numberOfStation)));
                    if (Math.abs(numberOfStation)>0){
                        if (Math.abs(numberOfStation) <= 9){
                            ticketPrice = 5;
                        }
                        else if (Math.abs(numberOfStation)>9){

                            if(Math.abs(numberOfStation)<17){
                                ticketPrice = 7;
                            }
                            else if(Math.abs(numberOfStation)>=17){
                                ticketPrice = 10;
                            }
                        }
                        if(getAge(national)>= 60){
                            ticketPrice = ticketPrice/2.0;
                        }

                        editor.putFloat("ticket",Float.parseFloat(String.valueOf(ticketPrice)));
                        editor.apply();
                        TicketPriceTextView.setText(ticketPrice + "");
                        //double after = getAcount()-ticketPrice;
                        //Toast.makeText(FromToStationsActivity.this, "account: " + getAcount() + "After: "+ after, Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });



        BackImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FromToStationsActivity.this,MainActivity.class));
            }
        });

        ConfirmTripTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String no_stations = NumberStationTextView.getText().toString();
                String ticket_price = TicketPriceTextView.getText().toString();
                if(no_stations.equals("") || ticket_price.equals("")){
                    Toast.makeText(FromToStationsActivity.this, "Please first select from & to stations then view price then confirm your trip", Toast.LENGTH_SHORT).show();
                }
                else {
                    Call<List<UserModel>> call = ApiConnection.getApiPlaceHolder().userLogin(1L);
                    call.enqueue(new Callback<List<UserModel>>() {
                        @Override
                        public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                            if (response.isSuccessful()) {
                                //Toast.makeText(FromToStationsActivity.this, "xx", Toast.LENGTH_SHORT).show();
                                List<UserModel> Users = response.body();
                                for (UserModel user: Users){
                                    if(user.getPhone().equals(number)){
                                        ACCOUNT = true;
                                        userAcc = user;
                                    } }
                                if(ACCOUNT){
                                    acc = userAcc.getAccount();
                                    if(acc < ticketPrice) {
                                        TicketPriceTextView.setBackgroundResource(R.drawable.red_button);
                                        AlertDialog.Builder alert = new AlertDialog.Builder(FromToStationsActivity.this);
                                        alert.setMessage("Your Account is low. charge money first \n\nYour Balance: " + acc + "\nTicket Price: " + ticketPrice)
                                                .setCancelable(false)
                                                .setPositiveButton("Add Money", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        startActivity(new Intent(FromToStationsActivity.this, FawryActivity.class));

                                                    }
                                                })
                                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        dialog.cancel();
                                                    }
                                                });
                                        AlertDialog alertDialog = alert.create();
                                        alertDialog.setTitle("Failed");
                                        alertDialog.show();
                                    }
                                    else{
                                        TicketPriceTextView.setBackgroundResource(R.drawable.green_button);
                                        AlertDialog.Builder alert = new AlertDialog.Builder(FromToStationsActivity.this);
                                        alert.setMessage("Confirming process" +  "\n\nYour Balance: " + acc + "\nTicket Price: " + ticketPrice + "\nAccount after payment: " + (acc-ticketPrice))
                                                .setCancelable(false)
                                                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        //Toast.makeText(FromToStationsActivity.this, "her", Toast.LENGTH_SHORT).show();
                                                        String formtext = FromTextView.getText().toString();
                                                        String totext = ToTextView.getText().toString();
                                                        Call<List<UserModel>> calla1 = ApiConnection.getApiPlaceHolder().userLogin(1L);
                                                        calla1.enqueue(new Callback<List<UserModel>>() {
                                                            @Override
                                                            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                                                                if (response.isSuccessful()){
                                                                    List<UserModel> Users = response.body();
                                                                    for (UserModel user: Users){
                                                                        if(user.getPhone().equals(number)){
                                                                            UPDATEACC = true;
                                                                            userUpdateAcc = user;
                                                                        }
                                                                    }
                                                                    if(UPDATEACC) {
                                                                        Call<Void> calla2 = ApiConnection.getApiPlaceHolder().delete(userUpdateAcc.getId());
                                                                        calla2.enqueue(new Callback<Void>() {
                                                                            @Override
                                                                            public void onResponse(Call<Void> call, Response<Void> response) {
                                                                            }
                                                                            @Override
                                                                            public void onFailure(Call<Void> call, Throwable t) {
                                                                            }
                                                                        });
                                                                        UserModel u;
                                                                        if (FavTrip.isChecked()) {
                                                                            u = new UserModel(userUpdateAcc.getName(),userUpdateAcc.getPhone(),userUpdateAcc.getPassword(),userUpdateAcc.getNational_id(),acc - ticketPrice,formtext,totext);
                                                                        }else{
                                                                            u = new UserModel(userUpdateAcc.getName(),userUpdateAcc.getPhone(),userUpdateAcc.getPassword(),userUpdateAcc.getNational_id(),userUpdateAcc.getAccount(),formtext,totext);
                                                                        }
                                                                        Call<UserModel> calla3 = ApiConnection.getApiPlaceHolder().addUser(u);
                                                                        calla3.enqueue(new Callback<UserModel>() {
                                                                            @Override
                                                                            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                                                                            }
                                                                            @Override
                                                                            public void onFailure(Call<UserModel> call, Throwable t) {
                                                                            }
                                                                        });
                                                                        String formtxt = FromTextView.getText().toString();String totxt = ToTextView.getText().toString();
                                                                        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
                                                                        int year = calendar.get(Calendar.YEAR);int month = calendar.get(Calendar.MONTH)+1;int day = calendar.get(Calendar.DAY_OF_MONTH);int Hours = calendar.get(Calendar.HOUR_OF_DAY);int Minutes = calendar.get(Calendar.MINUTE);int Seconds = calendar.get(Calendar.SECOND);
                                                                        String qrdata = number + "," + formtxt + "," + totxt + "," + year + "," + month + "," + day + "," + Hours + "," + Minutes + "," + Seconds;
                                                                        TripModel trip = new TripModel(number,year,month,day,Hours,Minutes,Seconds,qrdata,formtext,totext,ticketPrice,false,false);
                                                                        Call<TripModel> calltrip = ApiConnection.getApiPlaceHolder().addTrip(trip);
                                                                        calltrip.enqueue(new Callback<TripModel>() {
                                                                            @Override
                                                                            public void onResponse(Call<TripModel> call, Response<TripModel> response) {
                                                                            }
                                                                            @Override
                                                                            public void onFailure(Call<TripModel> call, Throwable t) {
                                                                            }
                                                                        });
                                                                    }
                                                                    UPDATEACC = false;
                                                                }
                                                            }

                                                            @Override
                                                            public void onFailure(Call<List<UserModel>> call, Throwable t) {

                                                            }
                                                        });
                                                        startActivity(new Intent(FromToStationsActivity.this, TicketActivity.class));

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

                                    ACCOUNT = false;
                                }
                            }
                        }
                        @Override
                        public void onFailure(Call<List<UserModel>> call, Throwable t) {
                        }
                    });


                }

            }
        });

    }

    public double getAge(String nationalId){
        int x = Integer.parseInt(String.valueOf(nationalId.charAt(1)));
        int y = Integer.parseInt(String.valueOf(nationalId.charAt(2)));
        String birthYear = "";
        int birthYearInt = 0;
            if(x > 2){
                birthYear = "19"+x+y;
            }
            else{
                birthYear = "20"+x+y;
            }

        birthYearInt = Integer.parseInt(birthYear);
        int year = Calendar.getInstance().get(Calendar.YEAR);
        age = year - birthYearInt;
        return age;
    }

    public void updateAccount(Double account){

        String formtext = FromTextView.getText().toString();String totext = ToTextView.getText().toString();
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int year = calendar.get(Calendar.YEAR);int month = calendar.get(Calendar.MONTH)+1;int day = calendar.get(Calendar.DAY_OF_MONTH);int Hours = calendar.get(Calendar.HOUR_OF_DAY);int Minutes = calendar.get(Calendar.MINUTE);int Seconds = calendar.get(Calendar.SECOND);
        String qrdata = number + "," + formtext + "," + totext + "," + year + "," + month + "," + day + "," + Hours + "," + Minutes + "," + Seconds;
        TripModel trip = new TripModel(number,year,month,day,Hours,Minutes,Seconds,qrdata,formtext,totext,ticketPrice,false,false);
        Call<TripModel> calltrip = ApiConnection.getApiPlaceHolder().addTrip(trip);
        calltrip.enqueue(new Callback<TripModel>() {
            @Override
            public void onResponse(Call<TripModel> call, Response<TripModel> response) {
            }
            @Override
            public void onFailure(Call<TripModel> call, Throwable t) {
            }
        });
        /*
        Call<List<UserModel>> call = ApiConnection.getApiPlaceHolder().userLogin(1L);
        call.enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                if (response.isSuccessful()){
                    List<UserModel> Users = response.body();
                    for (UserModel user: Users){
                        if(user.getPhone().equals(number)){
                            PHONE = true;
                            //userModel = user;
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
                        //userModel.setAccount(account);
                        Call<UserModel> calla = ApiConnection.getApiPlaceHolder().addUser(userModel);
                        calla.enqueue(new Callback<UserModel>() {
                            @Override
                            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                            }
                            @Override
                            public void onFailure(Call<UserModel> call, Throwable t) {
                            }
                        });

                        String formtext = FromTextView.getText().toString();String totext = ToTextView.getText().toString();
                        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
                        int year = calendar.get(Calendar.YEAR);int month = calendar.get(Calendar.MONTH)+1;int day = calendar.get(Calendar.DAY_OF_MONTH);int Hours = calendar.get(Calendar.HOUR_OF_DAY);int Minutes = calendar.get(Calendar.MINUTE);int Seconds = calendar.get(Calendar.SECOND);
                        String qrdata = number + "," + formtext + "," + totext + "," + year + "," + month + "," + day + "," + Hours + "," + Minutes + "," + Seconds;
                        TripModel trip = new TripModel(number,year,month,day,Hours,Minutes,Seconds,qrdata,formtext,totext,ticketPrice,false,false);
                        Call<TripModel> call1 = ApiConnection.getApiPlaceHolder().addTrip(trip);
                        call1.enqueue(new Callback<TripModel>() {
                            @Override
                            public void onResponse(Call<TripModel> call, Response<TripModel> response) {
                            }
                            @Override
                            public void onFailure(Call<TripModel> call, Throwable t) {
                            }
                        });

                    }
                    PHONE = false;
                }
            }
            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {
            }
        });

         */
    }

}