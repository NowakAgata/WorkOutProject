package com.example.workoutproject;

public class user {

    private String login;
    private String password;
    private String name;
    private int weight;
    private int height;
    private int weight_goal;
    private double BMI;

    public user() {
    }

    public user(String login, String password, String name, int weight, int height, int weight_goal, double BMI) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.weight_goal = weight_goal;
        this.BMI = BMI;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight_goal() {
        return weight_goal;
    }

    public void setWeight_goal(int weight_goal) {
        this.weight_goal = weight_goal;
    }

    public double getBMI() {
        return BMI;
    }

    public void setBMI(double BMI) {
        this.BMI = BMI;
    }
}
