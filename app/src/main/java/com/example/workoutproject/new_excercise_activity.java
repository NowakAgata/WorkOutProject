package com.example.workoutproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class new_excercise_activity extends AppCompatActivity {


    EditText nameTxt, partTxt, picTxt;
    String name, part, pic;

    private DatabaseReference mDatabase ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_excercise_activity);
        nameTxt = findViewById(R.id.nameTxt);
        partTxt = findViewById(R.id.partTxt);
        picTxt = findViewById(R.id.picTxt);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("workouts");
    }

    public void addExercise(View view) {
        name = nameTxt.getText().toString();
        part = partTxt.getText().toString();
        pic = picTxt.getText().toString();

        if(name.isEmpty() || part.isEmpty() || pic.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please, enter all data!", Toast.LENGTH_SHORT).show();
        } //TODO: dodać funkcję sprawdzającą unikalność nazwy ćwiczenia
        else {
            Workout temp = new Workout(name, part, pic);
            mDatabase.push().setValue(temp);
            Toast.makeText(getApplicationContext(), "new exercies added succesfully", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
