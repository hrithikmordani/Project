package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_patient_homepage extends Fragment implements View.OnClickListener {
    String email;
    String name;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_patient_homepage,container,false);
        Button book_doctor = v.findViewById(R.id.button_bookdoctor);
        Button read_disease = v.findViewById(R.id.button_read_more);
        Button book_lab = v.findViewById(R.id.button_book_lab);
        patient_homepage activity = (patient_homepage)getActivity();
        email = activity.getData();
        name = activity.getName();


        book_doctor.setOnClickListener(this);
        read_disease.setOnClickListener(this);
        book_lab.setOnClickListener(this);
        return v;

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_bookdoctor:
                Intent intent = new Intent(getContext(),book_doctor.class);
                intent.putExtra("Email",email);
                intent.putExtra("Name",name);
                startActivity(intent);
                break;
            case R.id.button_read_more:
                Intent intent1 = new Intent(getContext(),read_disease.class);
                startActivity(intent1);
                break;
            case R.id.button_book_lab:
                Intent intent2 = new Intent(getContext(),lab_test.class);
                intent2.putExtra("Email",email);
                intent2.putExtra("Name",name);
                startActivity(intent2);
                break;

        }

    }
}
