package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class register_2 extends AppCompatActivity {
    Button btn_finish;
    EditText dob,weight,height,bloodgroup;
    String name,contact,email;
    FirebaseFirestore fStore;
    String TAG;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_2);
        Intent intent = getIntent();
        name=intent.getStringExtra("name");
        contact=intent.getStringExtra("contact");
        email=intent.getStringExtra("email");

        fStore = FirebaseFirestore.getInstance();
        dob = findViewById(R.id.edit_text_dob);
        weight = findViewById(R.id.edit_text_weight);
        height = findViewById(R.id.edit_text_height);
        bloodgroup = findViewById(R.id.edit_text_blood_group);
        btn_finish=findViewById(R.id.btn_finish);

        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sdob = dob.getText().toString();
                String sweight = weight.getText().toString();
                String sheight = height.getText().toString();
                String sblood = bloodgroup.getText().toString();

                Map<String,Object> patient_details = new HashMap<>();
                patient_details.put("Name",name);
                patient_details.put("Contact",contact);
                patient_details.put("Email",email);
                patient_details.put("Date of Birth",sdob);
                patient_details.put("Weight",sweight);
                patient_details.put("Height",sheight);
                patient_details.put("Blood group",sblood);

                fStore.collection("patients_details").document(email).set(patient_details).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(register_2.this,"User Created",Toast.LENGTH_SHORT).show();

                        Intent gotohome = new Intent(register_2.this,login.class);
                        startActivity(gotohome);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG,"Error in creating user",e);
                    }
                });





            }
        });



    }
}