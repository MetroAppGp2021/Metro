package com.example.metrocard2020.scanning;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.metrocard2020.R;
import com.example.metrocard2020.features.TicketActivity;
import com.example.metrocard2020.login.ApiConnection;
import com.example.metrocard2020.main.MainActivity;
import com.example.metrocard2020.main.NewTraditionalActivity;
import com.example.metrocard2020.model.ScanModel;
import com.example.metrocard2020.model.TripModel;
import com.example.metrocard2020.model.UserModel;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntryQrActivity extends AppCompatActivity {
    ImageView BackImageView;
    TextView genQr, textView, done, FromTextViewMachine;
    ImageView imageView;
    Boolean DONE, TRIP;
    TripModel tripM;
    ScanModel scan;
    SharedPreferences preferences;
    String station = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_qr);
        BackImageView = findViewById(R.id.back_image_view);
        textView = findViewById(R.id.text_view);
        done = findViewById(R.id.done_text_view);
        FromTextViewMachine = findViewById(R.id.from_text_view_machine);

        preferences = getSharedPreferences("ticket", MODE_PRIVATE);
        station = preferences.getString("qrdata", "");
        String FromStation = preferences.getString("from", "");
        //Toast.makeText(this, "" + station, Toast.LENGTH_SHORT).show();

        textView.setText("Be Careful your trip From: " + FromStation);
        imageView = findViewById(R.id.entry_qr_image_view);
        QRGEncoder qrgEncoder = new QRGEncoder(station, null, QRGContents.Type.TEXT, 500);
        Bitmap qrBits = qrgEncoder.getBitmap();
        imageView.setImageBitmap(qrBits);

        BackImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EntryQrActivity.this, TicketActivity.class));
            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fromMachine = FromTextViewMachine.getText().toString();
                //Toast.makeText(EntryQrActivity.this, "" + fromMachine, Toast.LENGTH_SHORT).show();
                if (station.contains(fromMachine)) {
                    done.setBackgroundResource(R.drawable.green_button);
                    Call<List<TripModel>> call1 = ApiConnection.getApiPlaceHolder().getTrips(1L);
                    call1.enqueue(new Callback<List<TripModel>>() {
                        @Override
                        public void onResponse(Call<List<TripModel>> call, Response<List<TripModel>> response) {
                            if(response.isSuccessful()){
                                List<TripModel> Trips = response.body();
                                for (TripModel trip: Trips){
                                    if(station.contains(trip.getPhone()) && station.contains(trip.getForm_station()) && station.contains(trip.getTo_station()) && station.contains(trip.getYear().toString()) && station.contains(trip.getMonth().toString()) && station.contains(trip.getDay().toString()) && station.contains(trip.getHours().toString()) && station.contains(trip.getMinutes().toString()) && station.contains(trip.getSecond().toString())){
                                        TRIP = true;
                                        tripM = trip;
                                    }
                                }
                                if(TRIP){
                                    Call<Void> calldd = ApiConnection.getApiPlaceHolder().deleteTrip(tripM.getId());
                                    calldd.enqueue(new Callback<Void>() {
                                        @Override
                                        public void onResponse(Call<Void> call, Response<Void> response) {
                                        }
                                        @Override
                                        public void onFailure(Call<Void> call, Throwable t) {
                                        }
                                    });
                                    TripModel t = new TripModel(tripM.getPhone(),tripM.getYear(),tripM.getMonth(),tripM.getDay(),tripM.getHours(),tripM.getMinutes(),tripM.getSecond(),tripM.getQrdata(),tripM.getForm_station(),tripM.getTo_station(),tripM.getCost(),true,tripM.getExit());
                                    Call<TripModel> callaa = ApiConnection.getApiPlaceHolder().addTrip(t);
                                    callaa.enqueue(new Callback<TripModel>() {
                                        @Override
                                        public void onResponse(Call<TripModel> call, Response<TripModel> response) {
                                        }
                                        @Override
                                        public void onFailure(Call<TripModel> call, Throwable t) {

                                        }
                                    });
                                    startActivity(new Intent(EntryQrActivity.this, TicketActivity.class));
                                }else {
                                    done.setBackgroundResource(R.drawable.red_button);
                                    AlertDialog.Builder alert = new AlertDialog.Builder(EntryQrActivity.this);
                                    alert.setMessage("Entry QR Code Used please Check Exit one")
                                            .setCancelable(false)
                                            .setPositiveButton("get Exit Qr", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    startActivity(new Intent(EntryQrActivity.this, TicketActivity.class));

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
                            }
                        }
                        @Override
                        public void onFailure(Call<List<TripModel>> call, Throwable t) {
                        }
                    });
                } else {
                    done.setBackgroundResource(R.drawable.red_button);
                    AlertDialog.Builder alert = new AlertDialog.Builder(EntryQrActivity.this);
                    alert.setMessage("There is mistake in From station: " + fromMachine)
                            .setCancelable(false)
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
            }
        });
    }
}