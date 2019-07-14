package com.example.workoutproject;

import android.app.Application;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;

public class ApplicationClass extends Application {

    static public FirebaseDatabase mDatabase ;
    static public DatabaseReference refExercises;
    public static DatabaseReference refUsers;
    public static DatabaseReference refTrainings;

    public static ArrayList<Training> trainings;
    public static ArrayList<Workout> backList ;
    public static ArrayList<Workout> chestList ;
    public static ArrayList<Workout> shoulderList;
    public static ArrayList<Workout> glutesList;
    public static ArrayList<Workout> legList;
    public static ArrayList<Workout> bicepsList;
    public static ArrayList<Workout> tricepsList;
    public static ArrayList<Workout> absList;
    public static ArrayList<Workout> cardio;
    public static ArrayList<Workout> workoutsList;

    public static ArrayList<User> userList ;
    @Override
    public void onCreate() {
        super.onCreate();

        backList = new ArrayList<>();
        chestList = new ArrayList<>();
        shoulderList = new ArrayList<>();
        glutesList = new ArrayList<>();
        legList = new ArrayList<>();
        bicepsList = new ArrayList<>();
        tricepsList = new ArrayList<>();
        absList = new ArrayList<>();
        workoutsList = new ArrayList<>();
        userList = new ArrayList<>();
        trainings = new ArrayList<>();
        cardio = new ArrayList<>();

        mDatabase = FirebaseDatabase.getInstance();
        refExercises = mDatabase.getReference().child("workouts");
        refUsers = mDatabase.getReference().child("users");
        refTrainings = mDatabase.getReference().child("trainings");

        refExercises.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                backList.clear();
                chestList.clear();
                shoulderList.clear();
                glutesList.clear();
                legList.clear();
                bicepsList.clear();
                tricepsList.clear();
                absList.clear();
                workoutsList.clear();
                cardio.clear();
                for(DataSnapshot workoutSnapshot : dataSnapshot.getChildren()){
                    Workout current = workoutSnapshot.getValue(Workout.class);
                    String part = current.getBodyPart();

                    switch(part){
                        case "Plecy":
                            backList.add(current);
                            workoutsList.add(current);
                            break ;
                        case "Klata":
                            chestList.add(current);
                            workoutsList.add(current);
                            break ;
                        case "Barki":
                            shoulderList.add(current);
                            workoutsList.add(current);
                            break ;
                        case "Pośladki":
                            glutesList.add(current);
                            workoutsList.add(current);
                            break ;
                        case "Nogi":
                            legList.add(current);
                            workoutsList.add(current);
                            break ;
                        case "Biceps":
                            bicepsList.add(current);
                            workoutsList.add(current);
                            break ;
                        case "Triceps":
                            tricepsList.add(current);
                            workoutsList.add(current);
                            break ;
                        case "Brzuch":
                            absList.add(current);
                            workoutsList.add(current);
                            break ;
                        case "Cardio":
                            cardio.add(current);
                            workoutsList.add(current);
                            break;
                        default:
                            workoutsList.add(current);
                            break;
                    }
                }

    }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        refUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userList.clear();
                for(DataSnapshot userSnapshot : dataSnapshot.getChildren()){
                    userList.add(userSnapshot.getValue(User.class)) ;

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        refTrainings.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                trainings.clear();
                for(DataSnapshot workoutSnapshot : dataSnapshot.getChildren()){
                    trainings.add(workoutSnapshot.getValue(Training.class)) ;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
