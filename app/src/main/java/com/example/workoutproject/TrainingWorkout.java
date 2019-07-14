package com.example.workoutproject;

public class TrainingWorkout {

    private String trainingWorkoutName;
    private int series ;
    private int repetities ;
    private int duration ;

    public TrainingWorkout(String workout, int series, int repetities){
        this.trainingWorkoutName = workout;
        this.series = series;
        this.repetities = repetities;
        this.duration = 0;
    }

    public TrainingWorkout(String workout, int duration){
        this.trainingWorkoutName = workout;
        this.series = 0;
        this.repetities = 0;
        this.duration = duration;
    }
    public TrainingWorkout(){

    }

    public String getTrainingWorkoutName() {
        return trainingWorkoutName;
    }

    public void setTrainingWorkoutName(String trainingWorkoutName) {
        this.trainingWorkoutName = trainingWorkoutName;
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
