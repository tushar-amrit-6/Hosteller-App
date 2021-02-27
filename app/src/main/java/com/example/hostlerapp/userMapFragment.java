package com.example.hostlerapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link userMapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class userMapFragment extends Fragment implements OnMapReadyCallback{

    private GoogleMap mMap;
    Hostel [] hostel = new Hostel[10];

    GoogleMap.OnCircleClickListener onCircleClickListener = new GoogleMap.OnCircleClickListener() {
        @Override
        public void onCircleClick(Circle circle) {
            String hostel_no = (String) circle.getTag();
            int no=Integer.parseInt(hostel_no);
            if(hostel_no!=null){
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("HOSTEL NO "+hostel_no);
                builder.setMessage("No. of infected = "+hostel[no-1].infected+"\n"+"No. of symptomatic = "+hostel[no-1].symptomatic);

                builder.setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                    }
                }).setCancelable(true);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }
    };

    DatabaseReference databaseInfected;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public userMapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment userMapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static userMapFragment newInstance(String param1, String param2) {
        userMapFragment fragment = new userMapFragment();
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

        View view = inflater.inflate(R.layout.activity_maps, null, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);

        if(mapFragment!=null)
            mapFragment.getMapAsync(this);
        else Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        databaseInfected = FirebaseDatabase.getInstance().getReference().child("Infected");

        final ArrayList<Infected> infected_list = new ArrayList<>();

        databaseInfected.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                infected_list.clear();

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Infected inf = (dataSnapshot.getValue(Infected.class));
                    System.out.println(inf);
                    if(inf.name!=null) infected_list.add(inf);
                }

                mark_map(infected_list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "Fetch failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void mark_map(ArrayList<Infected> infected_list){

        int [] inf=new int[10],symp=new int[10];
        double [] lat = {23.209343,23.213672,23.212546,23.212817,23.215208,23.214009,23.212602,23.217121,23.207974,23.217042};
        double [] log = {77.411852,77.413495,77.406476,77.409787,77.408614,77.402222,77.405119,77.412042,77.405433,77.406557};

        for(Infected infected:infected_list){
            int hos = Integer.parseInt(infected.hostel_no);
            if(infected.type.equals("1")) inf[hos-1]++;
            else symp[hos-1]++;
        }

        LatLng manit = new LatLng(23.2131304,77.4033711);

        mMap.addMarker(new MarkerOptions().position(manit).title("MANIT"));
        mMap.animateCamera(
                CameraUpdateFactory.newLatLngZoom(
                        manit,16f
                )
        );

        for(int i=1;i<=10;i++){

            hostel[i-1] = new Hostel(Integer.toString(i),Integer.toString(inf[i-1]),Integer.toString(symp[i-1]),lat[i-1],log[i-1]);

            LatLng hos = new LatLng(hostel[i-1].getLat(),hostel[i-1].getLog());

            if(hostel[i - 1].infected.equals("0") && hostel[i-1].symptomatic.compareTo("3")<=0){
                mMap.addCircle(
                        new CircleOptions().center(hos).radius(50.0).strokeWidth(3f).strokeColor(Color.BLACK).
                                fillColor(Color.argb(50, 0, 255, 0)).clickable(true)
                ).setTag(Integer.toString(i));
            }

            else if(hostel[i - 1].infected.equals("0")){
                mMap.addCircle(
                        new CircleOptions().center(hos).radius(50.0).strokeWidth(3f).strokeColor(Color.BLACK).
                                fillColor(Color.argb(50, 255, 255, 0)).clickable(true)
                ).setTag(Integer.toString(i));
            }

            else{
                mMap.addCircle(
                        new CircleOptions().center(hos).radius(50.0).strokeWidth(3f).strokeColor(Color.BLACK).
                                fillColor(Color.argb(50, 255, 0, 0)).clickable(true)
                ).setTag(Integer.toString(i));
            }
        }

        mMap.setOnCircleClickListener(onCircleClickListener);

    }
}
