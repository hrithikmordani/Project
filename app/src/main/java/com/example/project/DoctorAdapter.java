package com.example.project;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import org.w3c.dom.Text;

public class DoctorAdapter extends FirestoreRecyclerAdapter <Example_doctor, DoctorAdapter.DoctorHolder> {

    public DoctorAdapter(@NonNull FirestoreRecyclerOptions<Example_doctor> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull DoctorHolder holder, int position, @NonNull Example_doctor model) {
        holder.Name.setText(model.getName());
        holder.Special.setText(model.getSpecialization());
        holder.Years.setText(model.getExperience());
        holder.Location.setText(model.getLocation());
        holder.Hospital.setText(model.getHospital());
    }

    @NonNull
    @Override
    public DoctorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_card,
                parent,false);
        return new DoctorHolder(v);
    }

    class DoctorHolder extends RecyclerView.ViewHolder{
        TextView Name;
        TextView Special;
        TextView Years;
        TextView Location;
        TextView Hospital;

        public DoctorHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.doc_card_name);
            Special = itemView.findViewById(R.id.doc_card_special);
            Years = itemView.findViewById(R.id.doc_card_experience);
            Location = itemView.findViewById(R.id.doc_card_location);
            Hospital = itemView.findViewById(R.id.doc_card_hospital);
        }
    }

}
