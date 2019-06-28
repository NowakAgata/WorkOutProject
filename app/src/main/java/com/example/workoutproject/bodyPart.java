package com.example.workoutproject;

public class bodyPart {

    private String name ;
    private int pic ;

    public bodyPart(String name, int pic){
        this.name = name ;
        this.pic = pic ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }
}
