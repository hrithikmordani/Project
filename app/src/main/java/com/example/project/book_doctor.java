package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class book_doctor extends AppCompatActivity {
    private FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    private CollectionReference doctor_details = fStore.collection("doctor_details");
    private DoctorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View screen = getWindow().getDecorView();
        int ui = View.SYSTEM_UI_FLAG_FULLSCREEN;
        screen.setSystemUiVisibility(ui);

        setContentView(R.layout.activity_book_doctor);
        setUpRecyclerView();

    }
    private void setUpRecyclerView(){
        Query query = doctor_details.orderBy("Experience",Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<Example_doctor> options = new FirestoreRecyclerOptions.Builder<Example_doctor>()
                .setQuery(query,Example_doctor.class).build();
        Intent intent = getIntent();
        String user_email = intent.getStringExtra("Email");
        String user_name = intent.getStringExtra("Name");
        adapter = new DoctorAdapter(options);
        RecyclerView recyclerView = findViewById(R.id.recyclerview_doctor);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new DoctorAdapter.onItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                String email = documentSnapshot.getString("Email");
                String name = documentSnapshot.getString("Name");
                String special = documentSnapshot.getString("Specialization");
                String years = documentSnapshot.getString("Experience");
                String location = documentSnapshot.getString("Location");
                String hospital = documentSnapshot.getString("Hospital");

                Intent intent = new Intent(book_doctor.this,select_time.class);
                intent.putExtra("Email",email);
                intent.putExtra("Name",name);
                intent.putExtra("Special",special);
                intent.putExtra("Years",years);
                intent.putExtra("Location",location);
                intent.putExtra("Hospital",hospital);
                intent.putExtra("User_email",user_email);
                intent.putExtra("Patient_name",user_name);

                startActivity(intent);
            }
        });
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