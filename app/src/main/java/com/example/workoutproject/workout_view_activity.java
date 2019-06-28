package com.example.workoutproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class workout_view_activity extends AppCompatActivity {

    ImageView img ;
    TextView nameView, partView;
    String workoutName, workoutPart ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_view);
        nameView = findViewById(R.id.exerciseNameTxtView);
        partView = findViewById(R.id.exercisePartTxtView);
        Intent i = getIntent();
        String workout = i.getStringExtra("WORKOUT");
        String temp[] = workout.split(";");
        workoutName = temp[0];
        workoutPart = temp[1];

        nameView.setText(workoutName);
        partView.setText(workoutPart);

    //TODO: dodać tutaj + na liście ćwiczeń  wyświetlanie zdjęcia cwiczenia
    }

    public void ok_button(View view) {
        finish();
    }
}
