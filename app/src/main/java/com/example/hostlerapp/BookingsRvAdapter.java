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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class BookingsRvAdapter extends RecyclerView.Adapter<BookingsRvAdapter.BookingsRvHolder> {

    public ArrayList<BookingsRvModel> bookingsRvModels;
    public Context context;

    public BookingsRvAdapter(ArrayList<BookingsRvModel> bookingsRvModels, Context context){
        this.bookingsRvModels = bookingsRvModels;
        this.context=context;
    }

    public class BookingsRvHolder extends RecyclerView.ViewHolder{

        TextView text_token,text_name,text_hostel_no;
        ImageView image_check;

        public BookingsRvHolder(@NonNull View itemView) {
            super(itemView);
            text_token = itemView.findViewById(R.id.t2);
            text_name = itemView.findViewById(R.id.t1);
            text_hostel_no = itemView.findViewById(R.id.t3);
            image_check = itemView.findViewById(R.id.img_check);
        }
    }

    @NonNull
    @Override
    public BookingsRvAdapter.BookingsRvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookings_rv_item_layout,parent,false);
        BookingsRvHolder bookingsRvHolder= new BookingsRvHolder(view);
        return bookingsRvHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookingsRvAdapter.BookingsRvHolder holder, int position) {
        BookingsRvModel currentItem = bookingsRvModels.get(position);
        holder.image_check.setImageResource(R.drawable.ic_baseline_check_circle_24);
        holder.text_token.setText(currentItem.getToken());
        holder.text_name.setText(currentItem.getName());
        holder.text_hostel_no.setText(currentItem.getHostel_no());

        holder.image_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("CHECKED-IN");
                builder.setMessage("Has the student checked-in ?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myref = database.getReference();

                        myref.child("Slots/"+currentItem.getToken()+"/checked_in").setValue(true);
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
        return bookingsRvModels.size();
    }
};