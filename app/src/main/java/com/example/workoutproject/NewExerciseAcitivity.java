package com.example.workoutproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

public class NewExerciseAcitivity extends AppCompatActivity {


    EditText nameTxt;
    String name, part, pic;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;

    private DatabaseReference mDatabase ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_excercise);
        nameTxt = findViewById(R.id.nameTxt);
        mDatabase = ApplicationClass.mDatabase.getReference().child("workouts");
        spinner = findViewById(R.id.partSpinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.body_parts, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                part = (String) parent.getItemAtPosition(position) ;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                part = "nothing" ;
            }
        });
    }

    public void addExercise(View view) {
        name = nameTxt.getText().toString();
        pic = name ;

        if(name.isEmpty() ){
            Toast.makeText(getApplicationContext(), "Wpisz nazwę ćwiczenia!", Toast.LENGTH_SHORT).show();
        } else if(part.equals("nothing")){
            Toast.makeText(getApplicationContext(), "Wybierz partię ciała!", Toast.LENGTH_SHORT).show();
        }
        else {
            Workout temp = new Workout(name, part, pic);
            mDatabase.push().setValue(temp);
            finish();
        }
    }
}
