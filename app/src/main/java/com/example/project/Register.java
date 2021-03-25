package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    Button btn_next;
    EditText name,contact,email,password;
    EditText confirmpassword;
    FirebaseAuth fAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fAuth = FirebaseAuth.getInstance();
        name = findViewById(R.id.edit_text_full_name);
        contact = findViewById(R.id.edit_text_contact_number);
        email = findViewById(R.id.edit_text_email_register);
        password = findViewById(R.id.edit_text_password);
        confirmpassword = findViewById(R.id.edit_text_confirm_password);
        btn_next = findViewById(R.id.button_register_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String semail = email.getText().toString().trim();
                String spassword = password.getText().toString().trim();
                String sconfirmpassword = confirmpassword.getText().toString().trim();
                if (TextUtils.isEmpty(semail)){
                    email.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(spassword)){
                    password.setError(("Password is required"));
                    return;
                }
                if(!sconfirmpassword.matches(spassword)){
                    password.setError("Password and confirm password do not match");
                    return;
                }

                String emailpattern =  "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if(!semail.matches(emailpattern)){
                    email.setError("Please enter valid email");
                    return;
                }
                fAuth.createUserWithEmailAndPassword(semail,spassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this,"User Created",Toast.LENGTH_SHORT).show();
                            Intent next = new Intent(Register.this,register_2.class);
                            startActivity(next);
                        }else{

                            Toast.makeText(Register.this,"Error !"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });




            }
        });
    }
}