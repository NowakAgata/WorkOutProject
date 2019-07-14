package com.example.workoutproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class StartTrainingActivity extends AppCompatActivity {

    TextView trainingName, workoutName, timer, seriesTxtView;
    Button button ;
    int howManyIterationsLeft ;
    int size;
    private static final int REP_DURATION = 1;
    private static final int REST_DURATION = 5;
    private static final int REP_REST_DURATION = 3 ;
    private static final String SERIE = "Seria";
    private static final String REST = "Odpoczynek" ;
    private static final String REP_REST = "Odpoczynek między seriami" ;
    private static final String DURATION = "Czas trwania: " ;
    private Training training ;
    private CountDownTimer countDownTimer;
    private boolean isTimerOn = false;
    int workoutCounter = 0;
    String[] strTab ;
    int[] intTab;

    private static final String TAG = "debugging training " ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_training);

        trainingName = findViewById(R.id.trainingViewTrainingName);
        workoutName = findViewById(R.id.trainingViewWorkoutName);
        seriesTxtView = findViewById(R.id.trainingViewSerie);
        timer = findViewById(R.id.timer) ;
        button = findViewById(R.id.nextWorkoutButton);

        int pos = getIntent().getIntExtra("POSITION", 0);
        training = ListOfTrainingsActivity.trainings.get(pos);
        ArrayList<TrainingWorkout> list = training.getList() ;
        size = 0;
        for(int i =0; i<list.size(); i++){
            if(list.get(i).getDuration() == 0){
                int s = list.get(i).getSeries();
                size += (s*2) ;
            } else{
                size += 2;
            }
        }
        strTab = new String[size];
        intTab = new int[size];
        setTabs(list);
        for (int i =0; i<strTab.length; i++){
            Log.d(TAG, "Str: " + strTab[i] + ", int: " +intTab[i]) ;
        }
        howManyIterationsLeft = size ;
        //i = howManyWorkouts ;

        trainingName.setText(training.getTrainingName());
        workoutName.setText(list.get(0).getTrainingWorkoutName());
        timer.setTextSize(20);
        timer.setText(R.string.start_training);




    }

    private void setTabs(ArrayList<TrainingWorkout> list){
        int size = list.size() ;
        int series, repetities, duration ;
        int counter = 0;

        for(int i =0; i<size; i++){
            TrainingWorkout current = list.get(i) ;
            if(current.getDuration()==0){
                series = current.getSeries();
                repetities = current.getRepetities();
                for(int j=0; j<series-1;j++){
                    strTab[counter] = SERIE+(j+1) ;
                    intTab[counter] = (repetities*REP_DURATION);
                    counter++;
                    strTab[counter] = REP_REST;
                    intTab[counter] = REP_REST_DURATION ;
                    counter++;
                }
                strTab[counter] = SERIE+(series) ;
                intTab[counter] = (repetities*REP_DURATION);
                counter++;
            } else{
                duration = current.getDuration();
                strTab[counter] = DURATION ;
                intTab[counter] = duration;
                counter++;
            }
            strTab[counter] = REST ;
            intTab[counter] = REST_DURATION ;
            counter++;
        }
    }

    public void nextExercise(View view) {

        button.setText(R.string.next);
        String key = "";
        int value =0;
        int i = size - howManyIterationsLeft ;

        if(i>1 && strTab[i-1].equals(REST)){
            workoutCounter++ ;
            String temp = training.getList().get(workoutCounter).getTrainingWorkoutName() ;
            workoutName.setText(temp);
        }

        if(howManyIterationsLeft ==0 && !isTimerOn){
            finish();
        }
        else if(!isTimerOn){
            key = strTab[i] ;
            value = intTab[i];

            seriesTxtView.setText(key);
            isTimerOn = true ;

            if((--howManyIterationsLeft)==0) {
                button.setText(R.string.end);
                timer.setTextSize(20);
                timer.setText(R.string.end_of_training);
            }
            startTimer(value);

        } else{
            Toast.makeText(this, "Nie przerywaj, dasz radę!", Toast.LENGTH_SHORT).show();
        }


    }

    private void startTimer(int duration){

        timer.setTextSize(100);
        duration *=1000;
        countDownTimer = new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String temp = "" + (millisUntilFinished/1000);
                timer.setText(temp);
            }

            @Override
            public void onFinish() {
                timer.setTextSize(20);
                timer.setText(R.string.done);
                isTimerOn = false ;
            }
        }.start();

    }



}
