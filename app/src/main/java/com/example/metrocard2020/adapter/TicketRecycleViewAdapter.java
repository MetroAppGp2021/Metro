package com.example.metrocard2020.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.metrocard2020.R;
import com.example.metrocard2020.model.TripModel;
import com.example.metrocard2020.scanning.EntryQrActivity;
import com.example.metrocard2020.scanning.ExitQrActivity;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class TicketRecycleViewAdapter extends RecyclerView.Adapter<TicketRecycleViewAdapter.ViewHolder>{
    private List<TripModel> trips = new ArrayList<>();
    Context mContext;
    SharedPreferences preferences;

    public TicketRecycleViewAdapter(List<TripModel> trips, Context mContext) {
        this.trips = trips;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public TicketRecycleViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TicketRecycleViewAdapter.ViewHolder holder, int position) {
        String data = "From: " + trips.get(position).getForm_station() + "\nTo: " + trips.get(position).getTo_station() + "\nDate: " + trips.get(position).getYear() + "/" + trips.get(position).getMonth() + "/" + trips.get(position).getDay() + "\nAt: " + trips.get(position).getHours() + ":" +trips.get(position).getMinutes() + ":" + trips.get(position).getSecond();
        holder.DataTextView.setText(data);
        preferences = mContext.getSharedPreferences("ticket",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        holder.EntryTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!trips.get(position).getEntry()){
                    Toast.makeText(mContext, "" + trips.get(position).getQrdata(), Toast.LENGTH_SHORT).show();
                    editor.putString("qrdata",trips.get(position).getQrdata());
                    editor.putString("from",trips.get(position).getForm_station());
                    editor.putString("to",trips.get(position).getTo_station());
                    editor.apply();
                    mContext.startActivity(new Intent(mContext, EntryQrActivity.class));
                }
                else {
                    Toast.makeText(mContext, "Already Scanned", Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.ExitTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!trips.get(position).getExit()){
                    Toast.makeText(mContext, "" + trips.get(position).getQrdata(), Toast.LENGTH_SHORT).show();
                    editor.putString("qrdata",trips.get(position).getQrdata());
                    editor.putString("from",trips.get(position).getForm_station());
                    editor.putString("to",trips.get(position).getTo_station());
                    editor.apply();
                    mContext.startActivity(new Intent(mContext, ExitQrActivity.class));
                }
                else {
                    Toast.makeText(mContext, "Already Scanned", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return trips.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView DataTextView, EntryTextView, ExitTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            DataTextView = (itemView).findViewById(R.id.data_text_view);
            EntryTextView = (itemView).findViewById(R.id.entry_text_view);
            ExitTextView = (itemView).findViewById(R.id.exit_text_view);

        }
    }
}
