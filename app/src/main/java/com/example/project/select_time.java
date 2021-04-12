package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class select_time extends AppCompatActivity {
    TextView Name;
    TextView Special;
    TextView Years;
    TextView Location;
    TextView Hospital;
    Button book;
    Button t1,t2,t3,t4,t5,t6,t7,t8,t9;
    String time_slot = null;
    String required_email,user_email;
    FirebaseFirestore fStore;
    String patient_name;
    String priority;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View screen = getWindow().getDecorView();
        int ui = View.SYSTEM_UI_FLAG_FULLSCREEN;
        screen.setSystemUiVisibility(ui);
        Intent intent = getIntent();
        fStore = FirebaseFirestore.getInstance();
        required_email = intent.getStringExtra("Email");
        user_email = intent.getStringExtra("User_email");
        patient_name=intent.getStringExtra("Patient_name");
        String name = intent.getStringExtra("Name");
        String special = intent.getStringExtra("Special");
        String years = intent.getStringExtra("Years");
        String location = intent.getStringExtra("Location");
        String hospital = intent.getStringExtra("Hospital");
        setContentView(R.layout.activity_select_time);
        Name = findViewById(R.id.doc_card_name3);
        Special = findViewById(R.id.doc_card_special3);
        Years = findViewById(R.id.doc_card_experience3);
        Location = findViewById(R.id.doc_card_location3);
        Hospital = findViewById(R.id.doc_card_hospital3);
        book = findViewById(R.id.btn_confirm_booking);
        Name.setText(name);
        Special.setText(special);
        Years.setText(years);
        Location.setText(location);
        Hospital.setText(hospital);



        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        t3 = findViewById(R.id.t3);
        t4 = findViewById(R.id.t4);
        t5 = findViewById(R.id.t5);
        t6 = findViewById(R.id.t6);
        t7 = findViewById(R.id.t7);
        t8 = findViewById(R.id.t8);
        t9 = findViewById(R.id.t9);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(time_slot == null){
                    time_slot = "09:00-09:15";
                    priority = "1";
                    t1.setBackgroundColor(getResources().getColor(R.color.deep_blue));
                    t1.setTextColor(getResources().getColor(R.color.black));

                }
                else{
                    Toast.makeText(select_time.this, "Can only book a single time slot", Toast.LENGTH_SHORT).show();
                }

            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(time_slot == null){
                    time_slot = "09:15-09:30";
                    priority = "2";
                    t2.setBackgroundColor(getResources().getColor(R.color.deep_blue));
                    t2.setTextColor(getResources().getColor(R.color.black));

                }
                else{
                    Toast.makeText(select_time.this, "Can only book a single time slot", Toast.LENGTH_SHORT).show();
                }

            }
        });
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(time_slot == null){
                    time_slot = "09:30-09:45";
                    priority = "3";
                    t3.setBackgroundColor(getResources().getColor(R.color.deep_blue));
                    t3.setTextColor(getResources().getColor(R.color.black));

                }
                else{
                    Toast.makeText(select_time.this, "Can only book a single time slot", Toast.LENGTH_SHORT).show();
                }

            }
        });
        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(time_slot == null){
                    time_slot = "09:45-10:00";
                    priority = "4";
                    t4.setBackgroundColor(getResources().getColor(R.color.deep_blue));
                    t4.setTextColor(getResources().getColor(R.color.black));

                }
                else{
                    Toast.makeText(select_time.this, "Can only book a single time slot", Toast.LENGTH_SHORT).show();
                }

            }
        });
        t5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(time_slot == null){
                    time_slot = "10:00-10:15";
                    priority = "5";
                    t5.setBackgroundColor(getResources().getColor(R.color.deep_blue));
                    t5.setTextColor(getResources().getColor(R.color.black));

                }
                else{
                    Toast.makeText(select_time.this, "Can only book a single time slot", Toast.LENGTH_SHORT).show();
                }

            }
        });
        t6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(time_slot == null){
                    time_slot = "10:15:10:30";
                    priority = "6";
                    t6.setBackgroundColor(getResources().getColor(R.color.deep_blue));
                    t6.setTextColor(getResources().getColor(R.color.black));

                }
                else{
                    Toast.makeText(select_time.this, "Can only book a single time slot", Toast.LENGTH_SHORT).show();
                }

            }
        });
        t7.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(time_slot == null){
                    time_slot = "10:30-10:45";
                    priority = "7";
                    t7.setBackgroundColor(getResources().getColor(R.color.deep_blue));
                    t7.setTextColor(getResources().getColor(R.color.black));

                }
                else{
                    Toast.makeText(select_time.this, "Can only book a single time slot", Toast.LENGTH_SHORT).show();
                }

            }
        });
        t8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(time_slot == null){
                    time_slot = "10:45-11:00";
                    priority = "8";
                    t8.setBackgroundColor(getResources().getColor(R.color.deep_blue));
                    t8.setTextColor(getResources().getColor(R.color.black));

                }
                else{
                    Toast.makeText(select_time.this, "Can only book a single time slot", Toast.LENGTH_SHORT).show();
                }

            }
        });
        t9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(time_slot == null){
                    time_slot = "11:00-11:15";
                    priority = "9";
                    t9.setBackgroundColor(getResources().getColor(R.color.deep_blue));
                    t9.setTextColor(getResources().getColor(R.color.black));

                }
                else{
                    Toast.makeText(select_time.this, "Can only book a single time slot", Toast.LENGTH_SHORT).show();
                }

            }
        });

        book.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                Map<String,Object> bookings = new HashMap<>();
                bookings.put("User_email",user_email);
                bookings.put("Doctor_email",required_email);
                bookings.put("Time_slot",time_slot);
                bookings.put("Patient_name",patient_name);
                bookings.put("Priority",priority);
                fStore.collection("Booking").document().set(bookings).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(select_time.this,"Booking confirmed",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(select_time.this,patient_homepage.class);
                        startActivity(intent);
                    }
                });





            }
        });



    }
}