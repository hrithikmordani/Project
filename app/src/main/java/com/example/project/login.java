package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class login extends AppCompatActivity {
    TextView register_here;
    EditText email,password;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String generated_email;


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
                fAuth.signInWithEmailAndPassword(semail,spassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            generated_email = fAuth.getCurrentUser().getEmail();
                            DocumentReference documentReference = fStore.collection("patients_details").document(generated_email);
                            DocumentReference documentReference1 = fStore.collection("doctor_details").document(generated_email);
                            DocumentReference documentReference2 = fStore.collection("lab_details").document(generated_email);
                            if(documentReference!=null){
                                Toast.makeText(login.this,"Login in Successful! Welcome Patient.",Toast.LENGTH_SHORT);
                                Intent intent = new Intent(login.this,MainActivity.class);
                                startActivity(intent);

                            }
                            else if(documentReference1!=null){
                                Toast.makeText(login.this,"Login in Successful! Welcome Doctor.",Toast.LENGTH_SHORT);
                                Intent intent = new Intent(login.this,doctor_homepage.class);
                                startActivity(intent);
                            }
                            else if(documentReference2!=null){
                                Toast.makeText(login.this,"Login in Successful! Welcome Lab.",Toast.LENGTH_SHORT);
                                Intent intent = new Intent(login.this,lab_homepage.class);
                                startActivity(intent);

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