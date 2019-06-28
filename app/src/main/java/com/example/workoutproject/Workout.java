package com.example.workoutproject;

public class Workout {

    private String name;
    private String bodyPart;
    private String pic;

    public Workout(String name, String bodyPart, String pic) {
        this.name = name;
        this.bodyPart = bodyPart;
        this.pic = pic;
    }
    public Workout(){
        this.name="";
        this.bodyPart="";
        this.pic ="";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBodyPart() {
        return bodyPart;
    }

    public void setBodyPart(String bodyPart) {
        this.bodyPart = bodyPart;
    }

    public String getPic(){
        return pic;
    }

    public void setPic(String pic){
        this.pic = pic ;
    }

}


