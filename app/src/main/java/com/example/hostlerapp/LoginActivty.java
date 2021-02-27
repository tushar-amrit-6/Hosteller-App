package com.example.hostlerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class LoginActivty extends AppCompatActivity {

        DatabaseReference databaseStudents;
        Button loginButton;
        EditText text_email;
        EditText text_password;
        SharedPreferences sharedPreferences;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //for changing status bar icon colors
            if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }

            setContentView(R.layout.activity_login_activty);

            loginButton=findViewById(R.id.cirLoginButton);
            text_email=findViewById(R.id.editTextEmailLogin);
            text_password=findViewById(R.id.editTextPasswordLogin);
            sharedPreferences = getSharedPreferences("com.example.hostlerapp",MODE_PRIVATE);

            if(sharedPreferences.getBoolean("login",false)){
                startActivity(new Intent(this,userActivity.class));
            }

            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("comp",text_email.getText().toString()+"xxx"+text_password.getText().toString());

                    if("admin".equals(text_email.getText().toString())
                            && "password".equals(text_password.getText().toString())){
                        Intent intent=new Intent(LoginActivty.this,adminActivity.class);
                        startActivity(intent);
                        return;
                    }

                    databaseStudents = FirebaseDatabase.getInstance().getReference().child("Students");

                    databaseStudents.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                Student stud = (dataSnapshot.getValue(Student.class));

                                Log.i("comp",stud.getEmail()+" "+text_email.getText().toString()+" "+stud.getPassword()+" "+text_password.getText().toString());

                                if(stud.getEmail().equals(text_email.getText().toString())
                                        && stud.getPassword().equals(text_password.getText().toString())){
                                    sharedPreferences.edit().putString("name",stud.getName()).apply();
                                    sharedPreferences.edit().putString("id",stud.getId()).apply();
                                    sharedPreferences.edit().putString("hostel_no",stud.getHostel_no()).apply();
                                    sharedPreferences.edit().putBoolean("login",true).apply();

                                    if(sharedPreferences.getString("slot_date","01/01/2021").equals("01/01/2021")){
                                        Intent intent = new Intent(LoginActivty.this,SlotBooking.class);
                                        startActivity(intent);
                                        return;
                                    }

                                    else {
                                        Intent intent = new Intent(LoginActivty.this, userActivity.class);
                                        startActivity(intent);
                                    }
                                }
                            }

                            text_email.setText("");
                            text_password.setText("");

                            Toast.makeText(LoginActivty.this, "Incorrect email or password!", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            });

        }
        public void onLoginClick(View view){
        startActivity(new Intent(this, RegisterActivity.class));
        overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);
        }

}