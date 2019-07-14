package com.example.workoutproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileSettingsActivity extends AppCompatActivity {

    String NOT_LOGGED_TEXT = ";not_logged;" ;

    String PREFERENCES_NAME = "myPreferences" ;
    String PREFERENCES_TEXT_LOGGED = "text" ;
    SharedPreferences prefs ;

    String userLogin ;
    TextView name, weight, height, goal, BMI;

    Button loggButton;
    static public User currentLogged ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);

        prefs = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);

        name = findViewById(R.id.userNameTxtView);
        weight = findViewById(R.id.userWeightTxtView);
        height = findViewById(R.id.userHeightTxtView);
        goal = findViewById(R.id.userWeightGoalTxtView);
        BMI = findViewById(R.id.userBMITxtView);


        loggButton = findViewById(R.id.loggOffButton) ;

        if(!MainActivity.isLogged){
            loggButton.setText(R.string.log_on);
            currentLogged = null ;
        }else{
            loggButton.setText(R.string.log_off);
            setData();
        }




    }


    public void setData(){
        String temp ;
        for(int i =0 ; i< ApplicationClass.userList.size() ; i++) {
            temp = ApplicationClass.userList.get(i).getLogin() ;
            if(temp.equals(MainActivity.login)){
                currentLogged = ApplicationClass.userList.get(i) ;
            }
        }
        if(currentLogged != null) {
            int w, h, g;
            String l = currentLogged.getLogin();
            w = currentLogged.getWeight();
            h = currentLogged.getHeight();
            g = currentLogged.getWeight_goal();
            double b = currentLogged.getBMI();
            temp = "Twój login w naszym serwisie: " + l ;
            name.setText(temp);
            temp = "Twoja waga to: " + String.valueOf(w) + " kg.";
            weight.setText(temp);
            temp = "Twój wzrost to: " + String.valueOf(h) + " cm.";
            height.setText(temp);
            temp = "Twoja wymarzona waga to: " + String.valueOf(g) + " kg.";
            goal.setText(temp);
            temp = "Twój wskaźnik BMI to: " + String.valueOf(g) + ".";
            BMI.setText(temp);
        } else{
            Toast.makeText(this, "Nie jesteś zalogowany", Toast.LENGTH_SHORT).show();
        }
    }

    public void okButton(View view) {
        finish();
    }

    public void loggOutButton(View view) {
        if(!MainActivity.isLogged){
            Intent i = new Intent(getApplicationContext(), Logging_activity.class);
            startActivityForResult(i, 1);
        } else{
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(PREFERENCES_TEXT_LOGGED, NOT_LOGGED_TEXT);
            editor.apply();
            MainActivity.isLogged = false ;
            MainActivity.login = NOT_LOGGED_TEXT ;
            finish();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            setData();
        }
    }
}
