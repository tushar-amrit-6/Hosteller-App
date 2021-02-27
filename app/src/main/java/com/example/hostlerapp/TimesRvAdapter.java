package com.example.hostlerapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hostlerapp.UpdateRecyclerView.UpdateRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TimesRvAdapter extends RecyclerView.Adapter<TimesRvAdapter.TimesRvViewHolder> {

    private ArrayList<TimesRvModel> items;
    int row_index=-1;
    UpdateRecyclerView updateRecyclerView;
    Activity activity;
    boolean select = false;

    public TimesRvAdapter(ArrayList<TimesRvModel> items, Activity activity, UpdateRecyclerView updateRecyclerView) {
        this.items = items;
        this.updateRecyclerView = updateRecyclerView;
        this.activity = activity;
    }

    @NonNull
    @Override
    public TimesRvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.times_rv_item,parent,false);
        TimesRvViewHolder timesRvViewHolder = new TimesRvViewHolder(view);

        return timesRvViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TimesRvViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public void onBindViewHolder(@NonNull TimesRvViewHolder holder, int position) {
        TimesRvModel currentItem = items.get(position);
        //holder.slotView.setText(userHomeFragment.stime[position]+" to "+userHomeFragment.etime[position]);
        holder.slotView.setText(items.get(position).getSlot());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index = position;
                notifyDataSetChanged();
                updateRecyclerView.callback(position);
            }
        });

        if(select){
            if(position==0)
                holder.cardView.setBackgroundResource(R.drawable.times_rv_selected_bg);
            select=false;
        }
        else{
            if(row_index == position){
                holder.cardView.setBackgroundResource(R.drawable.times_rv_selected_bg);
            }
            else{
                holder.cardView.setBackgroundResource(R.drawable.times_rv_bg);
            }
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class TimesRvViewHolder extends RecyclerView.ViewHolder{

        TextView slotView;
        CardView cardView;

        public TimesRvViewHolder(@NonNull View itemView) {
            super(itemView);
            slotView = itemView.findViewById(R.id.slot_times);
            cardView = itemView.findViewById(R.id.card_slide);
        }
    }
}
