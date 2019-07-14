package com.example.workoutproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {



    String PREFERENCES_NAME = "myPreferences" ;
    String PREFERENCES_TEXT_LOGGED = "text" ;
    String NOT_LOGGED_TEXT = ";not_logged;" ;
    SharedPreferences prefs ;

    private static int LOGIN_ACTIVITY_REQUEST_CODE = 1;
    int EXERCISE_LIST_ACTIVITY_REQUEST = 2;
    int TRAINING_LIST_ACTIVITY_REQUEST = 3;
    int STATISTICS_ACTIVITY_REQUEST = 4;
    int PROFILE_ACTIVITY_REQUEST = 5;
    int RANDOM_EXCERCISE_REQUEST = 6;


    TextView helloTxtView;
    ImageView test;
    public static String login ;
    public static boolean isLogged = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helloTxtView = findViewById(R.id.hello);
        prefs = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);


        getLogged();

        if(!isLogged){
            Intent intent = new Intent(getApplicationContext(), Logging_activity.class);
            startActivityForResult(intent, LOGIN_ACTIVITY_REQUEST_CODE);
        } else {
            setWelcomeTxt();
        }

    }
    //Checking if anybody is logged to the application
    //login = current User login;
    private void getLogged() {
        login = prefs.getString(PREFERENCES_TEXT_LOGGED, NOT_LOGGED_TEXT);
        isLogged = !(login.equals(NOT_LOGGED_TEXT));
    }
    private void setWelcomeTxt(){
        if(isLogged){
            String a = "Cześć " + login;
            helloTxtView.setText(a);
        } else{
            String a = "Nie jesteś zalogowany! Udaj się do widoku profilu!" ;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == LOGIN_ACTIVITY_REQUEST_CODE || requestCode == PROFILE_ACTIVITY_REQUEST){
           setWelcomeTxt();
        }
    }

    public void exerOnclick(View view) {
        Intent intent = new Intent(getApplicationContext(), ListOfExercisesActivity.class);
        startActivityForResult(intent,  EXERCISE_LIST_ACTIVITY_REQUEST);
    }


    public void trainOnClick(View view) {
        Intent intent = new Intent(getApplicationContext(), ListOfTrainingsActivity.class);
        startActivityForResult(intent, TRAINING_LIST_ACTIVITY_REQUEST);
    }

    public void profOnclick(View view) {
        Intent intent = new Intent(getApplicationContext(), ProfileSettingsActivity.class);
        startActivityForResult(intent, PROFILE_ACTIVITY_REQUEST);
    }



}
