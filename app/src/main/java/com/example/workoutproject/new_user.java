package com.example.workoutproject;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class new_user extends AppCompatActivity {

    String PREFERENCES_NAME = "myPreferences" ;
    String PREFERENCES_TEXT_LOGGED = "text" ;
    SharedPreferences prefs ;

    private DatabaseReference mDatabase ;
    TextView txtLogin, txtPassword, txtPassword2, txtName, txtWeight, txtHeight, txtGoal;
    String login, password, password2, name, weightStr, heightStr, goalStr;
    int weight, height, goal;
    double BMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users");
        txtLogin = findViewById(R.id.newLogin);
        txtPassword = findViewById(R.id.newPassword);
        txtPassword2 = findViewById(R.id.newPassword2);
        txtName = findViewById(R.id.newName);
        txtWeight = findViewById(R.id.newWeight);
        txtHeight = findViewById(R.id.newHeight);
        txtGoal = findViewById(R.id.newGoal);

    }

    public void submitButton(View view) {
        login = txtLogin.getText().toString();
        password = txtPassword.getText().toString();
        password2 = txtPassword2.getText().toString();
        name = txtName.getText().toString();
        weightStr = txtWeight.getText().toString();
        heightStr = txtHeight.getText().toString();
        goalStr = txtGoal.getText().toString();
        weight = Integer.parseInt(weightStr);
        height = Integer.parseInt(heightStr);
        goal = Integer.parseInt(goalStr);
        BMI = weight / (height*height) ;


        if(login.isEmpty() || password.isEmpty() || password2.isEmpty() || name.isEmpty()
                || weightStr.isEmpty() || heightStr.isEmpty() ||goalStr.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please, enter all data!", Toast.LENGTH_SHORT).show();
        } else if(!password.equals(password2)){
            Toast.makeText(getApplicationContext(), "Passwords aren't matching.", Toast.LENGTH_SHORT).show();
        } else if(weight < 10) {
            Toast.makeText(getApplicationContext(), "Are you sure your weight is right?", Toast.LENGTH_SHORT).show();
        } else if(height < 10) {
            Toast.makeText(getApplicationContext(), "Are you sure your height is right?", Toast.LENGTH_SHORT).show();
        } else if(goal < 10) {
            Toast.makeText(getApplicationContext(), "Are you sure your goal weight is right?", Toast.LENGTH_SHORT).show();
        } else if(!isLoginValid(login)){
            Toast.makeText(getApplicationContext(), "Try to enter a different login", Toast.LENGTH_SHORT).show();
        } else {
            user person = new user(login, password, name, weight, height, goal, BMI);
            mDatabase.push().setValue(person);
            Toast.makeText(getApplicationContext(), "new user added succesfully", Toast.LENGTH_SHORT).show();
            setLoggedUser(login);
            finish();
        }


    }

    private void setLoggedUser(String value) {
        prefs = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PREFERENCES_TEXT_LOGGED, value);
        editor.commit();
    }

    public boolean isLoginValid(String temp){
        for(user currentUser : Logging_activity.userList){
            if( temp.equals(currentUser.getLogin())){
                return false;
            }
        }
        return true;
    }


}
