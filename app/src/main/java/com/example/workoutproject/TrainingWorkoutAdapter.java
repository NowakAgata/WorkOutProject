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

public class TrainingWorkoutAdapter extends  RecyclerView.Adapter<TrainingWorkoutAdapter.ViewHolder> {

    private ArrayList<Training_workout> workouts ;
    private Context thisContext ;

public TrainingWorkoutAdapter(Context context, ArrayList<Training_workout> list){
        workouts= list;
        thisContext = context;
        }

public class ViewHolder extends RecyclerView.ViewHolder
{
    TextView txtName ;
    ImageView picView;
    public ViewHolder(@NonNull final View itemView){
        super(itemView);
        txtName = itemView.findViewById(R.id.trainingExerciseRowTextView);
    }
}

    @NonNull
    @Override
    public TrainingWorkoutAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.training_exercise_row, parent, false);
        return new TrainingWorkoutAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainingWorkoutAdapter.ViewHolder holder, int position) {

        holder.itemView.setTag(position);

        Training_workout temp = workouts.get(position) ;

        holder.txtName.setText(temp.getTraininWorkoutName());



    }

    @Override
    public int getItemCount() {
        return workouts.size();

    }
}
