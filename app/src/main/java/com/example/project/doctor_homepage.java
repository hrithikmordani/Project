package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class doctor_homepage extends AppCompatActivity {
    private FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    private CollectionReference ref = fStore.collection("Booking");
    private ScheduleAdapter adapter;
    String email;
    TextView tvname,tvexperience,tvhospital,tvspecial,tvlocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_homepage);
        View screen = getWindow().getDecorView();
        int ui = View.SYSTEM_UI_FLAG_FULLSCREEN;
        screen.setSystemUiVisibility(ui);
        tvname = findViewById(R.id.doc_card_name3);
        tvexperience = findViewById(R.id.doc_card_experience3);
        tvhospital = findViewById(R.id.doc_card_hospital3);
        tvspecial = findViewById(R.id.doc_card_special3);
        tvlocation = findViewById(R.id.doc_card_location3);
        Intent intent = getIntent();
        email = intent.getStringExtra("Email");
        DocumentReference documentReference = fStore.collection("doctor_details").document(email);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                tvname.setText(task.getResult().getString("Name"));
                tvexperience.setText(task.getResult().getString("Experience"));
                tvhospital.setText(task.getResult().getString("Hospital"));
                tvspecial.setText(task.getResult().getString("Specialization"));
                tvlocation.setText(task.getResult().getString("Location"));

            }
        });




        setUpRecyclerView();
    }
    private void setUpRecyclerView(){
        Query query = ref.whereEqualTo("Doctor_email", email);

        FirestoreRecyclerOptions<Schedule> options = new FirestoreRecyclerOptions.Builder<Schedule>()
                .setQuery(query,Schedule.class).build();
        adapter = new ScheduleAdapter(options);
        RecyclerView recyclerView = findViewById(R.id.rv_doctor_homepage);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.startListening();
    }

}