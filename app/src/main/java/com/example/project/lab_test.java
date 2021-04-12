package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class lab_test extends AppCompatActivity {
    String user_email;
    String user_name;
    Button book1,book2,book3,book4;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);
        Intent intent = getIntent();
        user_email= intent.getStringExtra("Email");
        user_name = intent.getStringExtra("Name");
        book1 = findViewById(R.id.button_chest_xray);
        book2 = findViewById(R.id.button_thyroid);
        book3=findViewById(R.id.button_lipid);
        book4 = findViewById(R.id.lumbar);
        book1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> booking = new HashMap<>();
                booking.put("Name",user_name);
                booking.put("Email",user_email);
                booking.put("Test_name","CRX");
                booking.put("Cost", "800");
                fStore.collection("Lab_bookings").document().set(booking).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(lab_test.this, "Booking confirmed", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(lab_test.this,patient_homepage.class);
                        startActivity(intent);

                    }
                });


            }
        });
        book2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> booking = new HashMap<>();
                booking.put("Name",user_name);
                booking.put("Email",user_email);
                booking.put("Test_name","Thyroid profile");
                booking.put("Cost", "600");
                fStore.collection("Lab_bookings").document().set(booking).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(lab_test.this, "Booking confirmed", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(lab_test.this,patient_homepage.class);
                        startActivity(intent);

                    }
                });

            }
        });
        book3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> booking = new HashMap<>();
                booking.put("Name",user_name);
                booking.put("Email",user_email);
                booking.put("Test_name","Lipid Profile");
                booking.put("Cost", "1500");
                fStore.collection("Lab_bookings").document().set(booking).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(lab_test.this, "Booking confirmed", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(lab_test.this,patient_homepage.class);
                        startActivity(intent);

                    }
                });

            }
        });
        book4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> booking = new HashMap<>();
                booking.put("Name",user_name);
                booking.put("Email",user_email);
                booking.put("Test_name","Lumbar X-ray");
                booking.put("Cost", "1500");
                fStore.collection("Lab_bookings").document().set(booking).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(lab_test.this, "Booking confirmed", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(lab_test.this,patient_homepage.class);
                        startActivity(intent);

                    }
                });


            }
        });


    }
}