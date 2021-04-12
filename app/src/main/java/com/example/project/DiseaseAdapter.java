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
import com.google.firebase.firestore.DocumentSnapshot;

import org.w3c.dom.Text;

public class DiseaseAdapter extends FirestoreRecyclerAdapter<Example_disease, DiseaseAdapter.DiseaseHolder>{
    private OnItemClickListener listener;

    public DiseaseAdapter(@NonNull FirestoreRecyclerOptions<Example_disease> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull DiseaseHolder holder, int position, @NonNull Example_disease model) {
        holder.Name.setText(model.getName());
        holder.Common.setText(model.getCommon());
        holder.Cases.setText(model.getCases());
        holder.Tag1.setText(model.getTag1());
        holder.Tag2.setText(model.getTag2());
        holder.Tag3.setText(model.getTag3());
        holder.Tag4.setText(model.getTag4());
        holder.Tag5.setText(model.getTag5());
        holder.Tag6.setText(model.getTag6());






    }

    @NonNull
    @Override
    public DiseaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.disease_card,parent,false);

        return new DiseaseHolder(v);
    }

    class DiseaseHolder extends RecyclerView.ViewHolder{
        TextView Name;
        TextView Cases;
        TextView Common;
        TextView Tag1;
        TextView Tag2;
        TextView Tag3;
        TextView Tag4;
        TextView Tag5;
        TextView Tag6;
        Button btn_readmore;

        public DiseaseHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.Name_d);
            Cases = itemView.findViewById(R.id.Cases_d);
            Common = itemView.findViewById(R.id.Common_d);
            Tag1 = itemView.findViewById(R.id.tag1_d);
            Tag2 = itemView.findViewById(R.id.tag2_d);
            Tag3 = itemView.findViewById(R.id.tag3_d);
            Tag4 = itemView.findViewById(R.id.tag4_d);
            Tag5 = itemView.findViewById(R.id.tag5_d);
            Tag6 = itemView.findViewById(R.id.tag6_d);
            btn_readmore = itemView.findViewById(R.id.btn_card_readmore);
            btn_readmore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    listener.onItemClick(getSnapshots().getSnapshot(position),position);

                }
            });


        }
    }
    public interface OnItemClickListener{
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
