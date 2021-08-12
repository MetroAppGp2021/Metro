package com.example.metrocard2020.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.metrocard2020.R;
import com.example.metrocard2020.model.TripModel;

import java.util.ArrayList;
import java.util.List;

public class HistoryRecycleViewAdapter extends RecyclerView.Adapter<HistoryRecycleViewAdapter.ViewHolder>{
    private List<TripModel> trips = new ArrayList<>();
    Context mContext;

    public HistoryRecycleViewAdapter(List<TripModel> trips, Context mContext) {
        this.trips = trips;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item,parent,false);
        HistoryRecycleViewAdapter.ViewHolder holder = new HistoryRecycleViewAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String date = trips.get(position).getYear() + "/" + trips.get(position).getMonth() + "/" + trips.get(position).getDay();
        String time = trips.get(position).getHours() + ":" + trips.get(position).getMinutes() + ":" + trips.get(position).getSecond();
        holder.FromTextView.setText(trips.get(position).getForm_station());
        holder.ToTextView.setText(trips.get(position).getTo_station());
        holder.DateTextView.setText(date);
        holder.TimeTextView.setText(time);
        holder.CostTextView.setText(String.valueOf(trips.get(position).getCost()));

    }

    @Override
    public int getItemCount() {
        return trips.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView FromTextView, ToTextView, DateTextView, TimeTextView, CostTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            FromTextView = (itemView).findViewById(R.id.from_text_view);
            ToTextView = (itemView).findViewById(R.id.to_text_view);
            DateTextView = (itemView).findViewById(R.id.date_text_view);
            TimeTextView = (itemView).findViewById(R.id.time_text_view);
            CostTextView = (itemView).findViewById(R.id.cost_text_view);
        }
    }
}
