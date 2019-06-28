package com.example.workoutproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class trainings_list_activity extends AppCompatActivity {

    RecyclerView recyclerView;
    TrainingAdapter trainingAdapter ;
    RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainings_list);


        recyclerView = findViewById(R.id.TrainingsListView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        trainingAdapter = new TrainingAdapter(this, ApplicationClass.trainings );
        recyclerView.setAdapter(trainingAdapter);

    }

    public void addNewTraining(View view) {
        Intent i = new Intent(getApplicationContext(), new_training_activity.class);
        startActivityForResult(i, 1);
    }

    public void trainingOnClick(View view) {
         int position = (int) view.getTag();
         Intent i = new Intent(getApplicationContext(), training_view_activity.class);
         i.putExtra("TRAINING", position);
         startActivity(i);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            trainingAdapter.notifyDataSetChanged();
        }
    }
}
