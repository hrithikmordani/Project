package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class read_disease extends AppCompatActivity {
    private FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    private CollectionReference disease_ref = fStore.collection("disease");
    private DiseaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View screen = getWindow().getDecorView();
        int ui = View.SYSTEM_UI_FLAG_FULLSCREEN;
        screen.setSystemUiVisibility(ui);

        setContentView(R.layout.activity_read_disease);
        setUpRecyclerView();

    }

    private void setUpRecyclerView() {
        Query query = disease_ref.orderBy("disease_num",Query.Direction.DESCENDING);
        FirestoreRecyclerOptions <Example_disease> options = new FirestoreRecyclerOptions.Builder<Example_disease>()
                .setQuery(query,Example_disease.class).build();
        adapter = new DiseaseAdapter(options);
        RecyclerView recyclerView = findViewById(R.id.recyclerview_disease);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new DiseaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                String id = documentSnapshot.getId();
                Toast.makeText(read_disease.this, "Position"+ position + "ID" + id, Toast.LENGTH_SHORT).show();
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