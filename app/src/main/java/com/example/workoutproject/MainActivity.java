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

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class MainActivity extends AppCompatActivity {



    String PREFERENCES_NAME = "myPreferences" ;
    String PREFERENCES_TEXT_LOGGED = "text" ;
    SharedPreferences prefs ;

    private static int LOGIN_ACTIVITY_REQUEST_CODE = 1;
    private static int EXERCISE_LIST_ACTIVITY_REQUEST = 2;
    private static int TRAINING_LIST_ACTIVITY_REQUEST = 3;
    private static int STATISTICS_ACTIVITY_REQUEST = 4;
    private static int PROFILE_ACTIVITY_REQUEST = 5;
    private static int RANDOM_EXCERCISE_REQUEST = 6;


    TextView helloTxtView;
    ImageView test;
    private String login ;
    public static boolean isLogged = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helloTxtView = findViewById(R.id.hello);
        test = findViewById(R.id.tempImgView);
        prefs = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);


        getLogged();

        if(!isLogged){
            Intent intent = new Intent(getApplicationContext(), Logging_activity.class);
            startActivityForResult(intent, LOGIN_ACTIVITY_REQUEST_CODE);
        } else {
            String a = "Hello " + login;
            helloTxtView.setText(a);
        }

    }
    //Checking if anybody is logged to the application
    //login = current user login;
    private void getLogged() {
        login = prefs.getString(PREFERENCES_TEXT_LOGGED, "");
        isLogged = !(login == "");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == LOGIN_ACTIVITY_REQUEST_CODE){
            getLogged();
            if(!login.equals("")){
                String a = "Hello " + login;
                helloTxtView.setText(a);
            }
        }
    }

    public void exerOnclick(View view) {
        Intent intent = new Intent(getApplicationContext(), exercises_list_activity.class);
        startActivityForResult(intent,  EXERCISE_LIST_ACTIVITY_REQUEST);
    }


    public void trainOnClick(View view) {
        Intent intent = new Intent(getApplicationContext(), trainings_list_activity.class);
        startActivityForResult(intent, TRAINING_LIST_ACTIVITY_REQUEST);
    }

    public void statsOnClick(View view) {
        Intent intent = new Intent(getApplicationContext(), statistics_activity.class);
        startActivityForResult(intent, STATISTICS_ACTIVITY_REQUEST);
    }

    public void profOnclick(View view) {
        Intent intent = new Intent(getApplicationContext(), profile_settings_activity.class);
        startActivityForResult(intent, PROFILE_ACTIVITY_REQUEST);
    }

    public void randOnClick(View view) {
        Intent intent = new Intent(getApplicationContext(), random_exercise_activity.class);
        startActivityForResult(intent, RANDOM_EXCERCISE_REQUEST);
    }

//    public void downloadPhotos(){
//        mStorageRef = mStorage.getInstance().getReference();
//        ref = mStorageRef.child("images/temp_logo.jpg");
//        final File tempFile = new File("images", "jpg");
//
//
//        ref.getFile(tempFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
//                Uri uri = taskSnapshot.getStorage();
//                test.setImageURI();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//
//            }
//        });
//
//
//    }
}
