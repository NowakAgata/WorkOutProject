package com.example.workoutproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TrainingAdapter extends RecyclerView.Adapter<TrainingAdapter.ViewHolder> {

    private ArrayList<Training> trainings ;
    private Context thisContext ;

    public TrainingAdapter(Context context, ArrayList<Training> list){
        trainings = list;
        thisContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtName ;
        ImageView picView;
        public ViewHolder(@NonNull final View itemView){
            super(itemView);
            txtName = itemView.findViewById(R.id.trainingTxtView);
            picView = itemView.findViewById(R.id.trainingImgView);
        }
    }

    @NonNull
    @Override
    public TrainingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.training_row, parent, false);
        return new TrainingAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainingAdapter.ViewHolder holder, int position) {

        holder.itemView.setTag(position);
        Training temp = trainings.get(position) ;
        holder.txtName.setText(temp.getTrainingName());
    }

    @Override
    public int getItemCount() {
        return trainings.size();

    }
}
