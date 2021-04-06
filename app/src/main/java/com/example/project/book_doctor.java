package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class book_doctor extends AppCompatActivity {
    private FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    private CollectionReference doctor_details = fStore.collection("doctor_details");
    private DoctorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_doctor);
        setUpRecyclerView();

    }
    private void setUpRecyclerView(){
        Query query = doctor_details.orderBy("Experience",Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<Example_doctor> options = new FirestoreRecyclerOptions.Builder<Example_doctor>()
                .setQuery(query,Example_doctor.class).build();
        adapter = new DoctorAdapter(options);
        RecyclerView recyclerView = findViewById(R.id.recyclerview_doctor);
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