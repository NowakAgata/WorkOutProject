package com.example.workoutproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class profile_settings_activity extends AppCompatActivity {

    String PREFERENCES_NAME = "myPreferences" ;
    String PREFERENCES_TEXT_LOGGED = "text" ;
    SharedPreferences prefs ;
    String userLogin ;
    TextView name, weight, height, goal, BMI;

    private FirebaseDatabase mDatabase ;
    static public DatabaseReference refUsers;
    static public user currentLogged ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);
        name = findViewById(R.id.userNameTxtView);
        weight = findViewById(R.id.userWeightTxtView);
        height = findViewById(R.id.userHeightTxtView);
        goal = findViewById(R.id.userWeightGoalTxtView);
        BMI = findViewById(R.id.userBMITxtView);

        mDatabase = FirebaseDatabase.getInstance();
        refUsers = mDatabase.getReference().child("users")
        ;
        prefs = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        userLogin = prefs.getString(PREFERENCES_TEXT_LOGGED, "");
        if(userLogin == ""){
            name.setText("Nie jesteś zalogowany!");
            currentLogged = null ;
        }else{
            name.setText(userLogin);
            refUsers.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

        //TODO:tutaj coś nie działa, naprawić
                    for(DataSnapshot userSnapshot : dataSnapshot.getChildren()){
                        user currentUser = userSnapshot.getValue(user.class);
                        String l = currentUser.getLogin() ;
                        if(l.equals(userLogin)){
                            currentLogged = currentUser;
                         }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {


                }
            });
            if(currentLogged != null) {
                int w, h, g;
                w = currentLogged.getWeight();
                h = currentLogged.getHeight();
                g = currentLogged.getWeight_goal();
                double b = currentLogged.getBMI();
                String temp = "Twoja waga to: " + String.valueOf(w) + " kg.";
                weight.setText(temp);
                temp = "Twój wzrost to: " + String.valueOf(h) + " cm.";
                height.setText(temp);
                temp = "Twoja wymarzona waga to: " + String.valueOf(g) + " kg.";
                goal.setText(temp);
                temp = "Twój wskaźnik BMI to: " + String.valueOf(g) + ".";
                BMI.setText(temp);
            }
        }




    }

    public void okButton(View view) {
        finish();
    }

    public void loggOutButton(View view) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PREFERENCES_TEXT_LOGGED, "");
        editor.apply();
        MainActivity.isLogged = false ;
        finish();
    }
}
