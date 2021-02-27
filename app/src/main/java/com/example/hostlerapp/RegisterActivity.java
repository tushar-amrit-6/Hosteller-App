package com.example.hostlerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    EditText name, scholar_no, address_line1, address_line2, city, state, country, mobile_no, hostel_no, room_no, email, password;
    Button signup;
    TextView existing;

    private boolean isValidMobile(String phone) {
        if (!Pattern.matches("[a-zA-Z]+", phone)) {
            return phone.length() == 10;
        }
        return false;
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    DatabaseReference databaseStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        changeStatusBarColor();

        //Setting the path to Firebase database
        databaseStudents = FirebaseDatabase.getInstance().getReference("Students");

        //Getting the views to all form elements
        name = findViewById(R.id.editTextName);
        scholar_no = findViewById(R.id.editScholarNumber);
        address_line1 = findViewById(R.id.editAddressLine1);
        address_line2 = findViewById(R.id.editAddressLine2);
        city = findViewById(R.id.editCity);
        state = findViewById(R.id.editState);
        country = findViewById(R.id.editCountry);
        hostel_no = findViewById(R.id.editHostelNumber);
        room_no = findViewById(R.id.editRoomNumber);
        mobile_no = findViewById(R.id.editTextMobile);
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
        signup = findViewById(R.id.cirRegisterButton);
        existing = findViewById(R.id.textRegisterButton);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRegisterClick();
            }
        });
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }


    public void onRegisterClick() {

        Log.w("click", "clciked");
        String sname, sscholar_no, saddress_line1, saddress_line2, scity, sstate, scountry, smobile_no, shostel_no, sroom_no, semail, spassword;

        sname = name.getText().toString().trim();
        sscholar_no = scholar_no.getText().toString().trim();
        saddress_line1 = address_line1.getText().toString().trim();
        saddress_line2 = address_line2.getText().toString().trim();
        scity = city.getText().toString().trim();
        sstate = state.getText().toString().trim();
        scountry = country.getText().toString().trim();
        shostel_no = hostel_no.getText().toString().trim();
        sroom_no = room_no.getText().toString().trim();
        smobile_no = mobile_no.getText().toString().trim();
        semail = email.getText().toString().trim();
        spassword = password.getText().toString().trim();

        //Checking for any empty/invalid fields
        if (sname.isEmpty()) {
            name.setError("This field cannot be left empty!");
            name.requestFocus();
            return;
        } else if (sscholar_no.isEmpty()) {
            scholar_no.setError("This field cannot be left empty!");
            scholar_no.requestFocus();
            return;
        } else if (saddress_line1.isEmpty()) {
            address_line1.setError("This field cannot be left empty!");
            address_line1.requestFocus();
            return;
        } else if (scity.isEmpty()) {
            city.setError("This field cannot be left empty!");
            city.requestFocus();
            return;
        } else if (sstate.isEmpty()) {
            state.setError("This field cannot be left empty!");
            state.requestFocus();
            return;
        } else if (scountry.isEmpty()) {
            country.setError("This field cannot be left empty!");
            country.requestFocus();
            return;
        } else if (shostel_no.isEmpty()) {
            hostel_no.setError("This field cannot be left empty!");
            hostel_no.requestFocus();
            return;
        } else if (sroom_no.isEmpty()) {
            room_no.setError("This field cannot be left empty!");
            room_no.requestFocus();
            return;
        } else if (!isValidMobile(smobile_no)) {
            mobile_no.setError("Enter a valid mobile number!");
            mobile_no.requestFocus();
            return;
        } else if (!isEmailValid(semail)) {
            email.setError("Enter a valid email-id!");
            email.requestFocus();
            return;
        } else if (spassword.length() <= 5) {
            password.setError("Chose a stronger password!");
            password.requestFocus();
            return;
        } else {             //all fields are correctly filled
            try {
                //adding to databse
                String id = databaseStudents.push().getKey();
                Student student = new Student(id, sname, sscholar_no, saddress_line1, saddress_line2, scity, sstate, scountry,  shostel_no, sroom_no, semail,smobile_no ,spassword);
                databaseStudents.child(id).setValue(student);

                //goto login activity
                startActivity(new Intent(this, LoginActivty.class));
                overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);
            } catch (Exception e) {
                Toast.makeText(this, "Error in database", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //onClick function for already existing account option
    public void onExistingClick(android.view.View view){
        startActivity(new Intent(this, LoginActivty.class));
        overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}
