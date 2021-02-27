package com.example.hostlerapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.holder>
{
    DatabaseReference databaseSlots;

    ArrayList<Model> data;
    Context context;
    public myAdapter(ArrayList<Model> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.singlerow,parent,false);
        return  new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        final Model temp = data.get(position);

        holder.tv1.setText(data.get(position).getText1());
        holder.tv2.setText(data.get(position).getText2());
        holder.tv3.setText(data.get(position).getText3());
        holder.tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("SLOT BOOKING");
                builder.setMessage("Are you sure want to book this slot ? ");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences sharedPreferences = context.getSharedPreferences("com.example.hostlerapp",Context.MODE_PRIVATE);
                        sharedPreferences.edit().putInt("slot_id" , holder.tv1.getText().toString().charAt(5)-'0').apply();
                        //Toast.makeText(context, "slot "+holder.tv1.getText().toString().charAt(5), Toast.LENGTH_SHORT).show();

                        databaseSlots = FirebaseDatabase.getInstance().getReference("Slots");

                        try {
                            String id = databaseSlots.push().getKey();

                            String name = sharedPreferences.getString("name","Shubham");
                            String hostel_no = sharedPreferences.getString("hostel_no","Shubham");
                            String token = sharedPreferences.getString("id","HsH94s");
                            String date = sharedPreferences.getString("slot_date","06/02/2021");

                            BookingsRvModel bookingsRvModel = new BookingsRvModel(token,name,hostel_no,date,holder.tv1.getText().toString().charAt(5)-'0');
                            databaseSlots.child(id).setValue(bookingsRvModel);

                            //goto login activity
                            Intent intent = new Intent(context,userActivity.class);
                            context.startActivity(intent);
                            return;

                        } catch (Exception e) {
                            Toast.makeText(context, "Error in database", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();

                    }
                }).setCancelable(true);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class holder extends RecyclerView.ViewHolder{

        TextView tv1,tv2,tv3;
        public holder(@NonNull View itemView) {
            super(itemView);
            tv1 = (TextView) itemView.findViewById(R.id.txt1);
            tv2 = (TextView) itemView.findViewById(R.id.txt2);
            tv3 = (TextView) itemView.findViewById(R.id.txt3);
        }
    }
}
