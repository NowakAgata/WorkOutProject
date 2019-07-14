package com.example.workoutproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListOfTrainingsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TrainingAdapter trainingAdapter ;
    RecyclerView.LayoutManager layoutManager;
    public static FirebaseDatabase mDatabase;
    public static DatabaseReference refTrainings;
    public static ArrayList<Training> trainings;
    String PREFERENCES_NAME = "myPreferences" ;
    String PREFERENCES_TEXT_LOGGED = "text" ;
    SharedPreferences prefs ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainings_list);

        trainings = new ArrayList<>();
        trainings = ApplicationClass.trainings ;

        recyclerView = findViewById(R.id.TrainingsListView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        trainingAdapter = new TrainingAdapter(this, trainings );
        recyclerView.setAdapter(trainingAdapter);

    }

    public void addNewTraining(View view) {
        Intent i = new Intent(getApplicationContext(), NewTrainingActivity.class);
        startActivityForResult(i, 1);
    }

    public void trainingOnClick(View view) {
         int position = (int) view.getTag();
         Intent i = new Intent(getApplicationContext(), TrainingViewActivity.class);
         i.putExtra("TRAINING", position);
         startActivityForResult(i, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            //TODO: naprawić, żeby nie wyświetlało się podwójnie po dodaniu nowego treningu
            finish();
        } else if(requestCode == 2){
            finish();
        }
    }
}
