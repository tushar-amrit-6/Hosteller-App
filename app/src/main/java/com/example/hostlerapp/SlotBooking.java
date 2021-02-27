package com.example.hostlerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.core.view.View;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class SlotBooking extends AppCompatActivity {
    TextView date;
    int day;
    String month;
    int year;
    RecyclerView rcv;
    myAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot_booking);
        date = ( TextView) findViewById(R.id.Date);

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.hostlerapp",MODE_PRIVATE);
        sharedPreferences.edit().putString("slot_date",date.getText().toString()).apply();


        Log.i("date",sharedPreferences.getString("slot_date","not set"));

        rcv = (RecyclerView) findViewById(R.id.rclview);
//        rcv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new myAdapter(dataqueue(),SlotBooking.this);
        rcv.setAdapter(adapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        rcv.setLayoutManager(gridLayoutManager);
    }
    public ArrayList<Model> dataqueue(){
        ArrayList<Model> holder = new ArrayList<>();
        Model ob1 = new Model();
        ob1.setText1("SLOT 1");
        ob1.setText2("Start Time: 8:00 A.M.\nto\nEnd Time : 9:00 A.M.");
        ob1.setText3("Available");

        holder.add(ob1);

        Model ob2 = new Model();
        ob2.setText1("SLOT 2");
        ob2.setText2("Start Time: 10:00 A.M.\nto\nEnd Time : 11:00 A.M.");
        ob2.setText3("Available");

        holder.add(ob2);

        Model ob3 = new Model();
        ob3.setText1("SLOT 3");
        ob3.setText2("Start Time: 12:00 A.M.\nto\nEnd Time : 1:00 P.M.");
        ob3.setText3("Available");

        holder.add(ob3);

        Model ob4 = new Model() ;
        ob4.setText1("SLOT 4");
        ob4.setText2("Start Time: 2:00 P.M.\nto\nEnd Time : 3:00 P.M.");
        ob4.setText3("Available");

        holder.add(ob4);

        Model ob5 = new Model();
        ob5.setText1("SLOT 5");
        ob5.setText2("Start Time: 4:00 P.M.\nto\nEnd Time : 5:00 P.M.");
        ob5.setText3("Available");

        holder.add(ob5);

        Model ob6 = new Model();
        ob6.setText1("SLOT 6");
        ob6.setText2("Start Time: 6:00 A.M.\nto\nEnd Time : 7:00 P.M.");
        ob6.setText3("Available");

        holder.add(ob6);

        return holder;
    }
}
