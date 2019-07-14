package com.example.workoutproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TrainingWorkoutDetailsActivity extends AppCompatActivity {


    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    int flag ;
    TextView seriesOrDurationTxt ;
    EditText seriesorDurationEdit, repetitiesEdit ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_workout_details);
        spinner = findViewById(R.id.detailsSpinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.workout_details, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        seriesOrDurationTxt = findViewById(R.id.seriesOrDurationTextView);
        seriesorDurationEdit = findViewById(R.id.seriesOrDurationEditText);
        repetitiesEdit = findViewById(R.id.repetitiesOnSeriesEditText);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                flag = position;


                //jeżeli flag == 0 to wybrano serie
                //jeżeli flag == 1 to wybrano czas trwania i trzeba zablokować
                // mozliwość wpisywania powtórzeń i zmienić textview
                if(position == 1){
                    seriesorDurationEdit.setHint("Czas trwania");
                    repetitiesEdit.setHint("----------------");

                } else{
                    seriesorDurationEdit.setHint("Ilość serii");
                    repetitiesEdit.setHint("Ilość powtórzeń");

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                flag = 3;
            }
        });
    }

    public void addExerciseToTrainingOnclick(View view) {
        if(flag == 3){
            Toast.makeText(this, "Wybierz opcję: serie czy czas!", Toast.LENGTH_SHORT).show();
        } else if( (flag == 1 && seriesorDurationEdit.getText().toString().isEmpty()) ||
            flag == 0 && seriesorDurationEdit.getText().toString().isEmpty() &&
                    repetitiesEdit.getText().toString().isEmpty()){
            Toast.makeText(this, "Wpisz wszystkie dane!", Toast.LENGTH_SHORT).show();
        } else if(flag == 0){
            int series, repetities;
            series = Integer.parseInt(seriesorDurationEdit.getText().toString()) ;
            repetities = Integer.parseInt(repetitiesEdit.getText().toString()) ;
            setResult(RESULT_OK,
                    new Intent().putExtra("FLAG",flag).putExtra("SERIES", series)
                            .putExtra("REPETITIES", repetities));
            finish();
        } else if(flag ==1){
            int duration = Integer.parseInt(seriesorDurationEdit.getText().toString());
            setResult(RESULT_OK,
                    new Intent().putExtra("FLAG", flag).putExtra("DURATION", duration));
            finish();
        } else{
            setResult(RESULT_CANCELED);
            finish();
        }

    }


}
