package com.example.workoutproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Logging_activity extends AppCompatActivity {

    private int NEW_USER_ACTIVITY = 1;
    EditText logEditTxt, passEditTxt ;
    String login, password;

    String PREFERENCES_NAME = "myPreferences" ;
    String PREFERENCES_TEXT_LOGGED = "text" ;
    SharedPreferences prefs ;

    public static List<User> userList;
    String TAG = "Ludzie z bazy ";
    static public DatabaseReference refUsers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logging_activity);

        logEditTxt = findViewById(R.id.loginEditTxt);
        passEditTxt = findViewById(R.id.passwordEditTxt);
        userList = new ArrayList<>();

        refUsers = ApplicationClass.mDatabase.getReference().child("users");

        refUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                userList.clear();
                for(DataSnapshot userSnapshot : dataSnapshot.getChildren()){
                    User currentUser = userSnapshot.getValue(User.class);
                    userList.add(currentUser);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());

            }
        });

    }

    public void loggingButton(View view) {

        login = logEditTxt.getText().toString();
        password = passEditTxt.getText().toString();

        if(login.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        } else if(validData(login, password)){
            setLoggedUser(login);
            finish();
        } else {
            Toast.makeText(this, "There is no such User or the password is wrong.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validData(String login, String password) {
        for(User currentUser : userList) {
            if(currentUser.getLogin().equals(login)){
                if(currentUser.getPassword().equals(password))
                    return true;
            }
        }
        return false;
    }

    public void registerButton(View view) {
        Intent i = new Intent(getApplicationContext(), NewUserActivity.class);
        startActivityForResult(i, NEW_USER_ACTIVITY);
    }

    private void setLoggedUser(String value) {
        prefs = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PREFERENCES_TEXT_LOGGED, value);
        editor.apply();

        MainActivity.isLogged = true ;
        MainActivity.login = value ;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == NEW_USER_ACTIVITY){
            finish();
        }
    }
}
