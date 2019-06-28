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

public class OnepartExerciseAdapter extends RecyclerView.Adapter<OnepartExerciseAdapter.ViewHolder> {

    private ArrayList<Workout> workouts ;
    private Context thisContext ;

    public OnepartExerciseAdapter(Context context, ArrayList<Workout> list){
        workouts= list;
        thisContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtName ;
        ImageView picView;
        public ViewHolder(@NonNull final View itemView){
            super(itemView);
            txtName = itemView.findViewById(R.id.singlePartTxtView);
            picView = itemView.findViewById(R.id.singlePartImgView);
        }
    }

    @NonNull
    @Override
    public OnepartExerciseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OnepartExerciseAdapter.ViewHolder holder, int position) {

        holder.itemView.setTag(position);

        Workout temp = workouts.get(position) ;

        holder.txtName.setText(temp.getName());
        holder.picView.setImageResource(R.drawable.sztangielki);


    }

    @Override
    public int getItemCount() {
        return workouts.size();

    }
}
