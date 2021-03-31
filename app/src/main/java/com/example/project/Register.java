package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button btn_next;
    EditText name,contact,email,password;
    EditText confirmpassword;
    FirebaseAuth fAuth;
    String text;
    FirebaseFirestore fStore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fStore = FirebaseFirestore.getInstance();
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Register.this,R.array.occupation, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
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
                String sname = name.getText().toString();
                String scontact  = contact.getText().toString();
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
                Map<String,Object> user_tag = new HashMap<>();

                user_tag.put("User_type",text);
                fAuth.createUserWithEmailAndPassword(semail,spassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            if (text.equals("Patient")){

                                fStore.collection("user_tag").document(semail).set(user_tag).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        String semail = email.getText().toString();
                                        String sname = name.getText().toString();
                                        String scontact  = contact.getText().toString();
                                        Intent pass = new Intent(Register.this,register_2.class);
                                        pass.putExtra("email",semail);
                                        pass.putExtra("name",sname);
                                        pass.putExtra("contact",scontact);

                                        startActivity(pass);

                                    }

                                });



                            }
                            if (text.equals("Doctor")){
                                fStore.collection("user_tag").document(semail).set(user_tag).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        String semail = email.getText().toString();
                                        String sname = name.getText().toString();
                                        String scontact  = contact.getText().toString();
                                        Intent pass = new Intent(Register.this,register_3.class);
                                        pass.putExtra("email",semail);
                                        pass.putExtra("name",sname);
                                        pass.putExtra("contact",scontact);

                                        startActivity(pass);
                                    }
                                });




                            }
                            if (text.equals("Lab Professional")){
                                fStore.collection("user_tag").document(semail).set(user_tag).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        String semail = email.getText().toString();
                                        String sname = name.getText().toString();
                                        String scontact  = contact.getText().toString();
                                        Intent pass = new Intent(Register.this,register_4.class);
                                        pass.putExtra("email",semail);
                                        pass.putExtra("name",sname);
                                        pass.putExtra("contact",scontact);

                                        startActivity(pass);
                                    }
                                });


                            }



                        }else{

                            Toast.makeText(Register.this,"Error !"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });




            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
