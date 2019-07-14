package com.example.workoutproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;


public class ListOfExercisesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    BodyPartsAdapter bodypartsAdapter ;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<BodyPart> bodyPartList;
    //jezeli flag==1 to znaczy, że przyszliśmy z dodawania treningu
    //a jezeli ==0, to po prostu wyświetlamy te ćwiczenia
    int flag ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_list);

        Intent i = getIntent();
        flag = i.getIntExtra("TRAINING_LIST", 0);

        bodyPartList = new ArrayList<>() ;
        bodyPartList.add( new BodyPart(" Plecy            ", R.drawable.plecy));
        bodyPartList.add( new BodyPart(" Klatka piersiowa ", R.drawable.klata));
        bodyPartList.add( new BodyPart(" Barki            ", R.drawable.barki));
        bodyPartList.add( new BodyPart(" Pośladki         ", R.drawable.posladki));
        bodyPartList.add( new BodyPart(" Nogi             ", R.drawable.nogi));
        bodyPartList.add( new BodyPart(" Biceps           ", R.drawable.biceps));
        bodyPartList.add( new BodyPart(" Triceps          ", R.drawable.triceps));
        bodyPartList.add( new BodyPart(" Brzuch           ", R.drawable.brzuch));
        bodyPartList.add( new BodyPart(" Cardio           ", R.drawable.cardio));
        bodyPartList.add( new BodyPart(" Wszystkie        ", R.drawable.sztangielki));


        recyclerView = findViewById(R.id.BodyPartsListView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        bodypartsAdapter = new BodyPartsAdapter(this, bodyPartList);
        recyclerView.setAdapter(bodypartsAdapter);


    }

    public void addNewExercise(View view) {
        Intent intent = new Intent(getApplicationContext(), NewExerciseAcitivity.class);
        startActivityForResult(intent, 1);
    }

    public void onBodyPartClick(View view) {
        int position = (int) view.getTag();
        String part = bodyPartList.get(position).getName();
        Intent intent = new Intent(getApplicationContext(), ListOfOnePartExercisesActivity.class);
        intent.putExtra("BODY_PART", part);
        intent.putExtra("FLAG", flag) ;
        startActivityForResult(intent, 2);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2 && resultCode == RESULT_OK ){
            int temp = data.getIntExtra("FLAG", 5);
            if(temp == 0 ){
                int series = data.getIntExtra("SERIES", 3);
                int repetities = data.getIntExtra("REPETITIES", 12) ;
                String selectedWorkoutName = data.getStringExtra("WORKOUT");
                setResult(RESULT_OK,
                        new Intent().putExtra("FLAG",temp).putExtra("SERIES", series)
                                .putExtra("REPETITIES", repetities)
                                .putExtra("WORKOUT", selectedWorkoutName));
                finish();
            } else if(temp == 1){
                int duration = data.getIntExtra("DURATION", 30);
                String selectedWorkoutName = data.getStringExtra("WORKOUT");
                setResult(RESULT_OK,
                        new Intent().putExtra("FLAG", temp).putExtra("DURATION", duration)
                                .putExtra("WORKOUT", selectedWorkoutName));
                finish();
            } else{
                setResult(RESULT_CANCELED);
                finish();
            }
        } else if(requestCode == 1){
            bodypartsAdapter.notifyDataSetChanged();
        }



    }
}
