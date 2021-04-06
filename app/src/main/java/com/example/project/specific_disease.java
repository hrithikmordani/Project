package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

public class specific_disease extends AppCompatActivity {
    FirebaseFirestore fStore;
    TextView heading,overview,symptoms,spread,conclusion;
    Button show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_disease);

        heading = findViewById(R.id.heading);
        conclusion = findViewById(R.id.conclusion);
        spread = findViewById(R.id.spread);
        symptoms = findViewById(R.id.symptoms);
        overview = findViewById(R.id.overview);
        show = findViewById(R.id.show);
        fStore = FirebaseFirestore.getInstance();


        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String choice = getIntent().getStringExtra("choice");
                fetchdata1(choice);
                show.setVisibility(View.GONE);
            }
        });

    }

    public void fetchdata1 (String c){
        DocumentReference document = fStore.collection("disease").document(c);
        document.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    heading.setText(documentSnapshot.getString("name"));
                    conclusion.setText(documentSnapshot.getString("conclusion"));
                    symptoms.setText(documentSnapshot.getString("symptoms"));
                    spread.setText(documentSnapshot.getString("spread"));
                    overview.setText(documentSnapshot.getString("overview"));

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