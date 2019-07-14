package com.example.workoutproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class ListOfOnePartExercisesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    OnepartExerciseAdapter bodypartsAdapter ;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Workout> temp ;
    View view;
    int flag ;
    String part;
    String selectedWorkoutName ;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_part_exercises_list);

        temp = new ArrayList<>();
        Intent i = getIntent();
        part = i.getStringExtra("BODY_PART");
        flag = i.getIntExtra("FLAG",0);

        switch(part){
            case " Plecy            ":
                temp = ApplicationClass.backList;
                break ;
            case " Klatka piersiowa ":
                temp = ApplicationClass.chestList;
                break ;
            case " Barki            ":
                temp = ApplicationClass.shoulderList;
                break ;
            case " Pośladki         ":
                temp = ApplicationClass.glutesList;
                break ;
            case " Nogi             ":
                temp = ApplicationClass.legList;
                break ;
            case " Biceps           ":
                temp = ApplicationClass.bicepsList;
                break ;
            case " Triceps          ":
                temp = ApplicationClass.tricepsList;
                break ;
            case " Brzuch           ":
                temp = ApplicationClass.absList;
                break ;
            case " Wszystkie        ":
                temp = ApplicationClass.workoutsList;
                break ;
            case " Cardio           ":
                temp = ApplicationClass.cardio;
                break;
            default:
                temp = ApplicationClass.workoutsList;
                break;
        }
        recyclerView = findViewById(R.id.OnePartListView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        bodypartsAdapter = new OnepartExerciseAdapter(this, temp );
        recyclerView.setAdapter(bodypartsAdapter);
    }

    public void onExerciseClick(View view) {
        int position = (int) view.getTag();
        Workout w = temp.get(position);
        selectedWorkoutName = w.getName();
        if(flag == 1){
            Intent i = new Intent(getApplicationContext(), TrainingWorkoutDetailsActivity.class);
            startActivityForResult(i, 1);
        } else{
            String ekstra = selectedWorkoutName+";"+part;
            Intent i = new Intent(getApplicationContext(), WorkoutViewActivity.class);
            i.putExtra("WORKOUT",ekstra);
            startActivity(i);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK ){
            int temp = data.getIntExtra("FLAG", 5);
            if(temp == 0 ){
                int series = data.getIntExtra("SERIES", 3);
                int repetities = data.getIntExtra("REPETITIES", 12) ;
                setResult(RESULT_OK,
                        new Intent().putExtra("FLAG",temp).putExtra("SERIES", series)
                                .putExtra("REPETITIES", repetities)
                                .putExtra("WORKOUT", selectedWorkoutName));
                finish();
            } else if(temp == 1){
                int duration = data.getIntExtra("DURATION", 30);
                setResult(RESULT_OK,
                        new Intent().putExtra("FLAG", temp).putExtra("DURATION", duration)
                                .putExtra("WORKOUT", selectedWorkoutName));
                finish();
            } else{
                setResult(RESULT_CANCELED);
                finish();
            }
        }


    }
}
