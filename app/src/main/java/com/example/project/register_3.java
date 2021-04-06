package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class register_3 extends AppCompatActivity {
    Button btn_finish;
    EditText hospital,special,experience,education;
    String name,contact,email;
    FirebaseFirestore fStore;
    String TAG;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_3);
        Intent intent = getIntent();
        name=intent.getStringExtra("name");
        contact=intent.getStringExtra("contact");
        email=intent.getStringExtra("email");

        fStore = FirebaseFirestore.getInstance();
        hospital = findViewById(R.id.edit_text_hosp);
        special = findViewById(R.id.edit_text_special);
        experience = findViewById(R.id.edit_text_exp);
        education = findViewById(R.id.edit_text_education);
        btn_finish=findViewById(R.id.btn_finish);

        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shosp = hospital.getText().toString();
                String sspecial = special.getText().toString();
                String sexp = experience.getText().toString();
                String sedu = education.getText().toString();

                Map<String,Object> doctor_details = new HashMap<>();
                doctor_details.put("Name",name);
                doctor_details.put("Contact",contact);
                doctor_details.put("Email",email);
                doctor_details.put("Experience",sexp + " yrs of exp. overall");
                doctor_details.put("Location",sedu);
                doctor_details.put("Specialization",sspecial);
                doctor_details.put("Hospital",shosp);

                fStore.collection("doctor_details").document(email).set(doctor_details).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(register_3.this,"User Created",Toast.LENGTH_SHORT).show();

                        Intent gotohome = new Intent(register_3.this,login.class);
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
