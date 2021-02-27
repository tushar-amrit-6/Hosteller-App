package com.example.hostlerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class userActivity extends AppCompatActivity {

    private ChipNavigationBar chipNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        chipNavigationBar=findViewById(R.id.bottomNavigationView);
        chipNavigationBar.setItemSelected(R.id.bottomNavigationView,true);
        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,new userHomeFragment()).commit();

        bottomMenu();

    }

    private void bottomMenu() {
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;
                switch (i) {
                    case R.id.miHome:
                        fragment = new userHomeFragment();
                        break;
                    case R.id.miChat:
                        fragment = new userChatFragment();
                        break;
                    case R.id.miMap:
                        fragment = new userMapFragment();
                        break;
                    case R.id.miSymptoms:
                        fragment = new userSymptoms();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, fragment).commit();
            }
        });
    }
}