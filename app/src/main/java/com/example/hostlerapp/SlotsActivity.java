//package com.example.hostlerapp;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.hostlerapp.UpdateRecyclerView.UpdateRecyclerView;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//
//
//public class SlotsActivity extends AppCompatActivity implements UpdateRecyclerView {
//
//    private RecyclerView recyclerView,recyclerView2;
//    private TimesRvAdapter timesRvAdapter;
//    LinearLayout button_left,button_right;
//
//    ArrayList<TimesRvModel> times_item = new ArrayList<>();
//
//
//    //Map<TimesRvModel,ArrayList<BookingsRvModel>> map=new TreeMap<TimesRvModel,ArrayList<BookingsRvModel>>();
//    ArrayList<BookingsRvModel>[] bookings = new ArrayList[6];
//
//    BookingsRvAdapter bookingsRvAdapter;
//    TextView date;
//
//    SharedPreferences sharedPreferences;
//
//    DatabaseReference databaseSlots;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_slots);
//
//        times_item.add(new TimesRvModel("Slot 1"));
//        times_item.add(new TimesRvModel("Slot 2"));
//        times_item.add(new TimesRvModel("Slot 3"));
//        times_item.add(new TimesRvModel("Slot 4"));
//        times_item.add(new TimesRvModel("Slot 5"));
//        times_item.add(new TimesRvModel("Slot 6"));
//
//        for (int i = 0; i < 6; i++) {
//            bookings[i] = new ArrayList<BookingsRvModel>();
//        }
//
//        date = findViewById(R.id.date_slot);
//        button_left = findViewById(R.id.arrow_left);
//        button_right = findViewById(R.id.arrow_right);
//
//        sharedPreferences = getSharedPreferences("com.example.hostlerapp",MODE_PRIVATE);
//
//        if(!sharedPreferences.getString("cur_date","-1").equals("-1")){
//            date.setText(sharedPreferences.getString("cur_date","-1"));
//        }
//
//        button_left.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String old_date = date.getText().toString();
//
//                int d = Integer.parseInt(old_date.substring(0,2));
//
//                if(d!=1)
//                    d--;
//
//                String new_dt = Integer.toString(d);
//                if(new_dt.length()==1)
//                    new_dt="0"+new_dt;
//
//                sharedPreferences.edit().putString("cur_date",new_dt+old_date.substring(2)).apply();
//
//                startActivity(new Intent(SlotsActivity.this,SlotsActivity.class));
//                finish();
//                return;
//            }
//        });
//
//        button_right.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String old_date = date.getText().toString();
//
//                int d = Integer.parseInt(old_date.substring(0,2));
//
//                if(d!=28)
//                    d++;
//
//                String new_dt = Integer.toString(d);
//                if(new_dt.length()==1)
//                    new_dt="0"+new_dt;
//
//                sharedPreferences.edit().putString("cur_date",new_dt+old_date.substring(2)).apply();
//
//                startActivity(new Intent(SlotsActivity.this,SlotsActivity.class));
//                finish();
//                return;
//            }
//        });
//
//        databaseSlots = FirebaseDatabase.getInstance().getReference().child("Slots");
//
//        databaseSlots.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    BookingsRvModel bm = (dataSnapshot.getValue(BookingsRvModel.class));
//                        if(bm.getText_date().equals(date.getText().toString())) {
//                            bookings[bm.getSlot_id()-1].add(bm);
//                        }
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//        recyclerView = findViewById(R.id.rclview1);
//        timesRvAdapter = new TimesRvAdapter(times_item,SlotsActivity.this,this);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
//        recyclerView.setAdapter(timesRvAdapter);
//
//
//        recyclerView2 = findViewById(R.id.rclview2);
//
//        bookingsRvAdapter = new BookingsRvAdapter(bookings[0],SlotsActivity.this);
//        recyclerView2.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
//        recyclerView2.setAdapter(bookingsRvAdapter);
//        //recyclerView2.setHasFixedSize(true);
//    }
//
//    @Override
//    public void callback(int position) {
//        bookingsRvAdapter = new BookingsRvAdapter(bookings[position],SlotsActivity.this);
//        recyclerView2.setAdapter(bookingsRvAdapter);
//    }
//}