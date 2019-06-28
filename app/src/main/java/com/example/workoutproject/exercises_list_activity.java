package com.example.workoutproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;


public class exercises_list_activity extends AppCompatActivity {

    RecyclerView recyclerView;
    BodypartsAdapter bodypartsAdapter ;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<bodyPart> bodyPartList;
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
        bodyPartList.add( new bodyPart(" Plecy            ", R.drawable.plecy));
        bodyPartList.add( new bodyPart(" Klatka piersiowa ", R.drawable.klata));
        bodyPartList.add( new bodyPart(" Barki            ", R.drawable.barki));
        bodyPartList.add( new bodyPart(" Pośladki         ", R.drawable.posladki));
        bodyPartList.add( new bodyPart(" Nogi             ", R.drawable.nogi));
        bodyPartList.add( new bodyPart(" Biceps           ", R.drawable.biceps));
        bodyPartList.add( new bodyPart(" Triceps          ", R.drawable.triceps));
        bodyPartList.add( new bodyPart(" Brzuch           ", R.drawable.brzuch));
        bodyPartList.add( new bodyPart(" Wszystkie        ", R.drawable.kettle));

        recyclerView = findViewById(R.id.BodyPartsListView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        bodypartsAdapter = new BodypartsAdapter(this, bodyPartList);
        recyclerView.setAdapter(bodypartsAdapter);


    }

    public void addNewExercise(View view) {
        Intent intent = new Intent(getApplicationContext(), new_excercise_activity.class);
        startActivityForResult(intent, 1);
    }

    public void onBodyPartClick(View view) {
        int position = (int) view.getTag();
        String part = bodyPartList.get(position).getName();
        Intent intent = new Intent(getApplicationContext(), one_part_exercises_list.class);
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


        //TODO: if(requestCode == 1) notifyDataSetChange ;
    }
}
