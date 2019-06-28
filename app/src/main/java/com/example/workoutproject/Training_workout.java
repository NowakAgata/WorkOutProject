package com.example.workoutproject;

public class Training_workout {

    private String traininWorkoutName ;
    private int series ;
    private int repetities ;
    private int duration ;

    public Training_workout(String workout, int series, int repetities){
        this.traininWorkoutName = workout;
        this.series = series;
        this.repetities = repetities;
        this.duration = 0;
    }

    public Training_workout(String workout, int duration){
        this.traininWorkoutName = workout;
        this.series = 0;
        this.repetities = 0;
        this.duration = duration;
    }
    public Training_workout(){

    }

    public String getTraininWorkoutName() {
        return traininWorkoutName;
    }

    public void setTraininWorkoutName(String traininWorkoutName) {
        this.traininWorkoutName = traininWorkoutName;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getRepetities() {
        return repetities;
    }

    public void setRepetities(int repetities) {
        this.repetities = repetities;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
