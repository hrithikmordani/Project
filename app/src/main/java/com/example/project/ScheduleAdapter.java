package com.example.project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class ScheduleAdapter extends FirestoreRecyclerAdapter<Schedule, ScheduleAdapter.ScheduleHolder> {

    public ScheduleAdapter(@NonNull FirestoreRecyclerOptions<Schedule> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ScheduleHolder holder, int position, @NonNull Schedule model) {
        holder.patientname.setText(model.getPatient_name());
        holder.timeslot.setText(model.getTime_slot());

    }

    @NonNull
    @Override
    public ScheduleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_card,parent,false);
        return new ScheduleHolder(v);
    }

    class ScheduleHolder extends RecyclerView.ViewHolder{
        TextView patientname;
        TextView timeslot;
        Button personalinfo;
        Button shared;

        public ScheduleHolder(@NonNull View itemView) {
            super(itemView);
            patientname = itemView.findViewById(R.id.schedule_card_patient_name);
            timeslot = itemView.findViewById(R.id.time_slot);
            personalinfo = itemView.findViewById(R.id.btn_personal_info);
            shared = itemView.findViewById(R.id.btn_shared_documents);


        }
    }
}
