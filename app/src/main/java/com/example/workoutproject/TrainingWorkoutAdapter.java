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

    private ArrayList<TrainingWorkout> workouts ;
    private Context thisContext ;

public TrainingWorkoutAdapter(Context context, ArrayList<TrainingWorkout> list){
        workouts= list;
        thisContext = context;
        }

public class ViewHolder extends RecyclerView.ViewHolder
{
    TextView txtName ;
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



        holder.txtName.setText(workouts.get(position).getTrainingWorkoutName());



    }

    @Override
    public int getItemCount() {
        return workouts.size();

    }
}
