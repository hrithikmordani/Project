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



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_patient_homepage,container,false);
        Button book_doctor = v.findViewById(R.id.button_bookdoctor);
        Button read_disease = v.findViewById(R.id.button_read_more);

        book_doctor.setOnClickListener(this);
        read_disease.setOnClickListener(this);
        return v;

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_bookdoctor:
                Intent intent = new Intent(getContext(),book_doctor.class);
                startActivity(intent);
                break;
            case R.id.button_read_more:
                Intent read = new Intent(getContext(),disease.class);
                startActivity(read);
        }

    }
}
