package com.example.hostlerapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link userHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class userHomeFragment extends Fragment {

    ImageView power;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    SharedPreferences sharedPreferences;

    static String[] stime= {"08:00","10:00","12:00","14:00","16:00","18:00"};
    static String[] etime= {"09:00","11:00","13:00","15:00","17:00","19:00"};

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public userHomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment userHomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static userHomeFragment newInstance(String param1, String param2) {
        userHomeFragment fragment = new userHomeFragment();
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

        sharedPreferences = this.getActivity().getSharedPreferences("com.example.hostlerapp",MODE_PRIVATE);;

        View view = inflater.inflate(R.layout.fragment_user_home, container, false);

        TextView text_name = view.findViewById(R.id.home_name);
        TextView text_id = view.findViewById(R.id.home_id);
        TextView text_slot = view.findViewById(R.id.home_slot);
        power = view.findViewById(R.id.power);

        text_name.setText("Hello " + sharedPreferences.getString("name","Tushar"));

        int id=sharedPreferences.getInt("slot_id",1);
        text_slot.setText("Time Slot \n"+  sharedPreferences.getString("slot_date","15/02/21")+"\n"+stime[id-1]+" to "+etime[id-1]);
        String s = sharedPreferences.getString("id","AHdbhd");

        text_id.setText("Unique ID\n" + s.substring(s.length()-6));

        power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("LOGOUT");
                builder.setMessage("Are you sure want to logout ? ");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("com.example.hostlerapp", Context.MODE_PRIVATE);
                        sharedPreferences.edit().putBoolean("login" , false).apply();
                        //sharedPreferences.edit().putInt("id" , false).apply();
                        Intent intent = new Intent(getActivity(),LoginActivty.class);
                        getActivity().startActivity(intent);
                        return;
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

        return view;
    }
}