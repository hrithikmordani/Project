package com.example.project;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class disease extends AppCompatActivity {
    TextView name1, overview1, name2, overview2;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    Button btn1, btn2, btnp1, btnp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease);
        name1 = findViewById(R.id.name1_disease);
        overview1 = findViewById(R.id.overview1_disease);
        name2 = findViewById(R.id.name2_disease);
        overview2 = findViewById(R.id.overview2_disease);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btnp1 = findViewById(R.id.btnpass1);
        btnp2 = findViewById(R.id.btnpass2);




        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchdata("malaria", name1, overview1, "name", "overview");
                btn1.setVisibility(View.GONE);

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchdata("dengue", name2, overview2, "name", "overview");
                btn2.setVisibility(View.GONE);

            }
        });

        btnp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = "malaria";
                Intent pass = new Intent(disease.this,specific_disease.class);
                pass.putExtra("choice",d);
                startActivity(pass);
            }
        });

        btnp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String c = "dengue";
                Intent pass = new Intent(disease.this,specific_disease.class);
                pass.putExtra("choice",c);
                startActivity(pass);
            }
        });
    }



    public void fetchdata (String x, TextView o, TextView p, String s, String t)
    {
        DocumentReference document = fStore.collection("disease").document(x);
        document.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    o.setText(documentSnapshot.getString(s));
                    p.setText(documentSnapshot.getString(t));

                } else {
                    Toast.makeText(getApplicationContext(), "Row not found", Toast.LENGTH_LONG).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();

            }
        });
    }
}