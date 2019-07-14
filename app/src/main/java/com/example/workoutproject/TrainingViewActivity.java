package com.example.workoutproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class TrainingViewActivity extends AppCompatActivity {

    ImageView imgView;
    TextView txtView;
    String day, name ;
    ArrayList<TrainingWorkout> list ;
    RecyclerView recyclerView;
    TrainingWorkoutAdapter trainingWorkoutAdapter;
    RecyclerView.LayoutManager layoutManager;
    int pos ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_view);

        imgView = findViewById(R.id.trainingViewImgView);
        txtView = findViewById(R.id.trainingViewNameTxtView);


        pos = getIntent().getIntExtra("TRAINING", 0);
        Training training = ApplicationClass.trainings.get(pos);

        list = new ArrayList<>();
        list = training.getList();

        day = training.getDay();
        name = training.getTrainingName();
        txtView.setText(name);

        recyclerView = findViewById(R.id.trainingWorkoutsRecyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        trainingWorkoutAdapter = new TrainingWorkoutAdapter(getApplicationContext(), list);
        recyclerView.setAdapter(trainingWorkoutAdapter);

        setPic();

    }

    private void setPic() {
        int tempDrawable ;
        switch(day)
        {
            case"Poniedziałek":
                tempDrawable = R.drawable.poniedzialek;
                break;
            case"Wtorek":
                tempDrawable = R.drawable.wtorek;
                break ;
            case"Środa":
                tempDrawable = R.drawable.sroda;
                break;
            case "Czwartek" :
                tempDrawable = R.drawable.czwartek ;
                break ;
            case"Piątek":
                tempDrawable = R.drawable.piatek;
                break;
            case "Sobota":
                tempDrawable = R.drawable.sobota;
                break;
            case "Niedziela" :
                tempDrawable = R.drawable.niedziela ;
                break;
            default:
                    tempDrawable = R.drawable.poniedzialek;
                    break;
        }
        imgView.setImageResource(tempDrawable);
    }

    public void startTraining(View view) {
        Intent i = new Intent(getApplicationContext(), StartTrainingActivity.class ) ;
        i.putExtra("POSITION", pos) ;
        startActivityForResult(i, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            finish();
        }
    }
}
