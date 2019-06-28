package com.example.workoutproject;

import java.util.ArrayList;

public class Training {

    private String userLogin ;
    private String trainingName ;
    private String day ;
    private ArrayList<Training_workout> list;

    public Training(String userLog,String name, String day, ArrayList<Training_workout> list) {
        this.userLogin = userLog;
        this.trainingName = name;
        this.day = day;
        this.list = list;
    }

    public Training(String userLog, String name, String day){
        this.trainingName = name;
        this.userLogin = userLog;
        this.day = day;
        this.list = null;
    }

    public Training(){
//        this.trainingName = null;
//        this.userLogin = null;
//        this.day = null;
//        this.list = null;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public void addExercise(Training_workout workout){
        list.add(workout);
    }

    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String name) {
        this.trainingName = name;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public ArrayList<Training_workout> getList() {
        return list;
    }

    public void setList(ArrayList<Training_workout> list) {
        this.list = list;
    }
}
