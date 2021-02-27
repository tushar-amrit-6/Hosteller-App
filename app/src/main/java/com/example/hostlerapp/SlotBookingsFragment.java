package com.example.hostlerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hostlerapp.UpdateRecyclerView.UpdateRecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SlotBookingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SlotBookingsFragment extends Fragment implements UpdateRecyclerView{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView,recyclerView2;
    private TimesRvAdapter timesRvAdapter;
    LinearLayout button_left,button_right;

    ArrayList<TimesRvModel> times_item = new ArrayList<>();

    ArrayList<BookingsRvModel>[] bookings = new ArrayList[6];

    BookingsRvAdapter bookingsRvAdapter;
    TextView date;

    SharedPreferences sharedPreferences;

    DatabaseReference databaseSlots;

    public SlotBookingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SlotBookingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SlotBookingsFragment newInstance(String param1, String param2) {
        SlotBookingsFragment fragment = new SlotBookingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_slots, container, false);

        times_item.add(new TimesRvModel("Slot 1"));
        times_item.add(new TimesRvModel("Slot 2"));
        times_item.add(new TimesRvModel("Slot 3"));
        times_item.add(new TimesRvModel("Slot 4"));
        times_item.add(new TimesRvModel("Slot 5"));
        times_item.add(new TimesRvModel("Slot 6"));

        for (int i = 0; i < 6; i++) {
            bookings[i] = new ArrayList<BookingsRvModel>();
        }

        date = view.findViewById(R.id.date_slot);
        button_left = view.findViewById(R.id.arrow_left);
        button_right = view.findViewById(R.id.arrow_right);

        sharedPreferences = getActivity().getSharedPreferences("com.example.hostlerapp",MODE_PRIVATE);

        if(!sharedPreferences.getString("cur_date","-1").equals("-1")){
            date.setText(sharedPreferences.getString("cur_date","-1"));
        }

        button_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String old_date = date.getText().toString();

                int d = Integer.parseInt(old_date.substring(0,2));

                if(d!=1)
                    d--;

                String new_dt = Integer.toString(d);
                if(new_dt.length()==1)
                    new_dt="0"+new_dt;

                sharedPreferences.edit().putString("cur_date",new_dt+old_date.substring(2)).apply();

                Fragment f = new SlotBookingsFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.flAdminFragment, f).commit();

                return;
            }
        });

        button_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String old_date = date.getText().toString();

                int d = Integer.parseInt(old_date.substring(0,2));

                if(d!=28)
                    d++;

                String new_dt = Integer.toString(d);
                if(new_dt.length()==1)
                    new_dt="0"+new_dt;

                sharedPreferences.edit().putString("cur_date",new_dt+old_date.substring(2)).apply();

                Fragment f = new SlotBookingsFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.flAdminFragment, f).commit();

                return;
            }
        });

        databaseSlots = FirebaseDatabase.getInstance().getReference().child("Slots");

        databaseSlots.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    BookingsRvModel bm = (dataSnapshot.getValue(BookingsRvModel.class));
                    if(bm.getText_date().equals(date.getText().toString()) && bm.getChecked_in()==false) {
                        bookings[bm.getSlot_id()-1].add(bm);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        recyclerView = view.findViewById(R.id.rclview1);
        timesRvAdapter = new TimesRvAdapter(times_item,getActivity(),this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(timesRvAdapter);


        recyclerView2 = view.findViewById(R.id.rclview2);

        bookingsRvAdapter = new BookingsRvAdapter(bookings[0],getActivity());
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView2.setAdapter(bookingsRvAdapter);
        //recyclerView2.setHasFixedSize(true);

        return view;
    }

    @Override
    public void callback(int position) {
        bookingsRvAdapter = new BookingsRvAdapter(bookings[position],getActivity());
        recyclerView2.setAdapter(bookingsRvAdapter);
    }
}