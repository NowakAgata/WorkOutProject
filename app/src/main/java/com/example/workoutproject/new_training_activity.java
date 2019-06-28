package com.example.workoutproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class new_training_activity extends AppCompatActivity {

    private DatabaseReference mDatabase ;

    EditText trainingNameEdit;
    Spinner daysSpinner ;
    ArrayAdapter<CharSequence> adapter;
    String newTrainingName, newTrainingDay;
    ArrayList<Training_workout> newTrainingList ;
    Training newTraining ;
    RecyclerView recyclerView;
    TrainingWorkoutAdapter trainingWorkoutAdapter ;
    RecyclerView.LayoutManager layoutManager;
    String PREFERENCES_NAME = "myPreferences" ;
    String PREFERENCES_TEXT_LOGGED = "text" ;
    SharedPreferences prefs ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_training);
        daysSpinner = findViewById(R.id.newTrainingSpinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.week_days, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daysSpinner.setAdapter(adapter);

        trainingNameEdit = findViewById(R.id.newTrainingNameEditText);
        newTrainingList = new ArrayList<>();

        recyclerView = findViewById(R.id.littleTraingRecyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        trainingWorkoutAdapter = new TrainingWorkoutAdapter(this, newTrainingList);
        recyclerView.setAdapter(trainingWorkoutAdapter);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("trainings");

        prefs = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);

        daysSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                newTrainingDay = (String) parent.getItemAtPosition(position) ;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                newTrainingDay = "poniedziałek" ;
            }
        });
    }

    public void addTraining(View view) {
        if(trainingNameEdit.getText().toString().isEmpty()){
            Toast.makeText(this, "Podaj nazwe treningu", Toast.LENGTH_SHORT).show();
        }
        else if(newTrainingList.isEmpty()){
            Toast.makeText(this, "Lista ćwiczeń jest pusta, dodaj conajmniej jedno ćwiczenie", Toast.LENGTH_SHORT).show();
        } else{
            newTrainingName = trainingNameEdit.getText().toString();
            String login = prefs.getString(PREFERENCES_TEXT_LOGGED, "user");


            newTraining = new Training(login,newTrainingName,newTrainingDay,newTrainingList );
            mDatabase.push().setValue(newTraining);
            finish();
        }
    }



    public void addExerciseToTraining(View view) {
        Intent i = new Intent(getApplicationContext(), exercises_list_activity.class );
        i.putExtra("TRAINING_LIST", 1);
        startActivityForResult(i, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK ){
            int temp = data.getIntExtra("FLAG", 5);
            if(temp == 0 ){
                int series = data.getIntExtra("SERIES", 3);
                int repetities = data.getIntExtra("REPETITIES", 12) ;
                String selectedWorkoutName = data.getStringExtra("WORKOUT");
                newTrainingList.add(new Training_workout(selectedWorkoutName, series, repetities));
                trainingWorkoutAdapter.notifyDataSetChanged();
            } else if(temp == 1){
                int duration = data.getIntExtra("DURATION", 30);
                String selectedWorkoutName = data.getStringExtra("WORKOUT");
                newTrainingList.add(new Training_workout(selectedWorkoutName, duration));
                trainingWorkoutAdapter.notifyDataSetChanged();
            }
        }
    }
}


