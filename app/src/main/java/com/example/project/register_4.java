package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class register_4 extends AppCompatActivity {
    Button btn_finish;
    EditText address,hosp,exp;
    String name,contact,email;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_4);
        Intent intent = getIntent();
        name=intent.getStringExtra("name");
        contact=intent.getStringExtra("contact");
        email=intent.getStringExtra("email");
        fStore = FirebaseFirestore.getInstance();
        address = findViewById(R.id.edit_text_address);
        hosp = findViewById(R.id.edit_text_hosp);
        exp = findViewById(R.id.edit_text_exp);

        btn_finish=findViewById(R.id.btn_finish);

        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String saddress = address.getText().toString();
                String shosp = hosp.getText().toString();
                String sexp = exp.getText().toString();

                Map<String,Object> lab_details = new HashMap<>();
                lab_details.put("Name",name);
                lab_details.put("Contact",contact);
                lab_details.put("Email",email);
                lab_details.put("Address",saddress);
                lab_details.put("Experience",sexp);
                lab_details.put("Hospital/Clinic",shosp);

                fStore.collection("lab_details").add(lab_details).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("TAG","Document snapshot added with ID:" +documentReference.getId());
                    }
                });
                Toast.makeText(register_4.this,"User Created",Toast.LENGTH_SHORT).show();
                Intent gotohome = new Intent(register_4.this,MainActivity.class);
                startActivity(gotohome);

            }
        });
    }
}