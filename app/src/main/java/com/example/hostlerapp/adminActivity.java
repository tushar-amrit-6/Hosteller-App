package com.example.hostlerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class adminActivity extends AppCompatActivity {
    private ChipNavigationBar chipNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        chipNavigationBar=findViewById(R.id.adminbottomNavigationView);
        chipNavigationBar.setItemSelected(R.id.adminbottomNavigationView,true);
        getSupportFragmentManager().beginTransaction().replace(R.id.flAdminFragment,new adminHomeFragment()).commit();

        bottomMenu();

    }

    private void bottomMenu() {
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;
                switch (i) {
                    case R.id.miAdminHome:
                        fragment = new adminHomeFragment();
                        break;
                    case R.id.miAdminReview:
                        fragment = new SlotBookingsFragment();
                        break;
                    case R.id.miMap:
                        fragment = new userMapFragment();
                        break;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.flAdminFragment, fragment).commit();
            }
        });
    }
}