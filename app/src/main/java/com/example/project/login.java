package com.example.project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Source;

public class login extends AppCompatActivity {
    TextView register_here;
    EditText email,password;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String text;
    String got_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        register_here = findViewById(R.id.textview_login_register);
        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        Button ab = findViewById(R.id.ab);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        register_here.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login_to_register = new Intent(login.this,Register.class);
                startActivity(login_to_register);
            }
        });
        ab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String semail = email.getText().toString().trim();
                String spassword = password.getText().toString().trim();
                if (TextUtils.isEmpty(semail)){
                    email.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(spassword)){
                    password.setError(("Password is required"));
                    return;
                }
                String emailpattern =  "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if(!semail.matches(emailpattern)){
                    email.setError("Please enter valid email");
                    return;
                }

                DocumentReference documentReference = fStore.collection("user_tag").document(semail);
                documentReference.get(Source.SERVER).addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            text = document.getString("User_type");
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(login.this,"Can't fetch tag",Toast.LENGTH_SHORT).show();
                    }
                });
                DocumentReference documentReference1 = fStore.collection("patients_details").document(semail);
                documentReference1.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot document1 = task.getResult();
                            got_name = document1.getString("Name");
                            Toast.makeText(login.this,"Got name of user "+got_name,Toast.LENGTH_SHORT).show();


                        }
                    }
                });
                fAuth.signInWithEmailAndPassword(semail,spassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            if (text.equals("Patient")){
                                Intent patientmain = new Intent(login.this,patient_homepage.class);
                                patientmain.putExtra("email",semail);

                                patientmain.putExtra("Name",got_name);
                                startActivity(patientmain);
                            }
                            else if(text.equals("Doctor")){
                                Intent doctormain = new Intent(login.this,doctor_homepage.class);
                                startActivity(doctormain);
                            }
                            else{
                                Intent labmain = new Intent(login.this,lab_homepage.class);
                                startActivity(labmain);
                            }
                        }
                        else{
                            Toast.makeText(login.this,"Error !"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });



            }
        });
    }
}